package it.sella.sample.microservice.springcloudzuulratelimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudZuulRatelimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulRatelimitApplication.class, args);
	}

	@Bean
	public ThrottlingPreFilter throttlingPreFilter() {
		return new ThrottlingPreFilter();
	}

}
