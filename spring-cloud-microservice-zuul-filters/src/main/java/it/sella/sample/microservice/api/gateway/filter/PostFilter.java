package it.sella.sample.microservice.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

/**
 * Created by gbs04154 on 28-02-2019.
 */
public class PostFilter extends ZuulFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        LOGGER.debug("Post filter executed");
        RequestContext context = RequestContext.getCurrentContext();
        String path = context.getRequest().getRequestURI();
        if(!"/api/static".equals(path)) throw new ZuulRuntimeException(new ZuulException("User Exception", 500, "User error description"));
        return null;
    }
}
