package it.sella.sample.microservice.api.gateway.microservicegatewayedge;

import it.sella.sample.microservice.api.gateway.filter.ErrorFilter;
import it.sella.sample.microservice.api.gateway.filter.PostFilter;
import it.sella.sample.microservice.api.gateway.filter.PreFilter;
import it.sella.sample.microservice.api.gateway.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class MicroserviceGatewayEdgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayEdgeApplication.class, args);
	}

	@Bean
	public PreFilter getPreFilterBean(){
		return new PreFilter();
	}

	@Bean
	public RouteFilter getRouteFilterBean(){
		return new RouteFilter();
	}

	@Bean
	public PostFilter getPostFilterBean(){
		return new PostFilter();
	}

	@Bean
	public ErrorFilter getErrorFilterBean(){
		return new ErrorFilter();
	}

}

