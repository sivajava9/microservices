package it.sella.sample.microservice.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gbs04154 on 28-02-2019.
 */
public class PreFilter extends ZuulFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        String path = context.getRequest().getRequestURI();
        return "/api/static".equals(path);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // Set the default response code for static filters to be 200
        ctx.setResponseStatusCode(HttpStatus.SC_OK);
        // first StaticResponseFilter instance to match wins, others do not set body and/or status
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody("static content server by static filters");
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}
