package it.sella.sample.microservice.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gbs04154 on 15-02-2019.
 */
public class RouteFilter extends ZuulFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(RouteFilter.class);

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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        LOGGER.debug("{} request to {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString());
        return null;
    }

}
