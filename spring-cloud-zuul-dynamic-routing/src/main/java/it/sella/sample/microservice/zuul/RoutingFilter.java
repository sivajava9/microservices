package it.sella.sample.microservice.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by gbs04154 on 28-02-2019.
 */
public class RoutingFilter extends ZuulFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(RoutingFilter.class);

    private static final String REQUEST_PATH = "/first";
    private static final String TARGET_SERVICE = "second-service";
    public static final String HTTP_METHOD = "POST";

    public RoutingFilter(DiscoveryClient discoveryClient){
        this.discoveryClient = discoveryClient;
    }

    public final DiscoveryClient discoveryClient;

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        LOGGER.debug("Request Method : " + method);
        LOGGER.debug("Request URI : " + requestURI);
        return HTTP_METHOD.equalsIgnoreCase(method) && requestURI.startsWith(REQUEST_PATH);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(TARGET_SERVICE);
        if(serviceInstances != null && serviceInstances.size() > 0){
            try {
                URL routeHost = serviceInstances.get(0).getUri().toURL();
                LOGGER.debug("Routing Host : " + routeHost.toString());
                context.setRouteHost(routeHost);

            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Couldn't get service URL!", e);
            }
        }else {
            throw new IllegalStateException("Target service instance not found!");
        }
        return null;
    }
}
