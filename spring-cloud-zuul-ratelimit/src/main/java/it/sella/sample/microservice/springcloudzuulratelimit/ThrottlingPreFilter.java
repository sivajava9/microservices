package it.sella.sample.microservice.springcloudzuulratelimit;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by gbs04154 on 26-02-2019.
 */
public class ThrottlingPreFilter extends ZuulFilter {

    private final HttpStatus tooManyRequests = HttpStatus.TOO_MANY_REQUESTS;

    // Permit a request only once every 10 seconds
    private final RateLimiter rateLimiter = RateLimiter.create(2.0D / 10.0D);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext currentContext = RequestContext.getCurrentContext();
            if (!rateLimiter.tryAcquire()) {
//                currentContext.unset();
//                HttpServletResponse response = currentContext.getResponse();
//                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                response.setStatus(this.tooManyRequests.value());
//                response.getWriter().append(this.tooManyRequests.getReasonPhrase());
                currentContext.getResponse().setContentType(MediaType.TEXT_PLAIN_VALUE);
                currentContext.setResponseStatusCode(this.tooManyRequests.value());
                currentContext.setResponseBody(this.tooManyRequests.getReasonPhrase());
                currentContext.setSendZuulResponse(false);

//                throw new ZuulException(this.tooManyRequests.getReasonPhrase(),
//                        this.tooManyRequests.value(),
//                        this.tooManyRequests.getReasonPhrase());
            }
        } catch (Exception e) {
//            ReflectionUtils.rethrowRuntimeException(e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
