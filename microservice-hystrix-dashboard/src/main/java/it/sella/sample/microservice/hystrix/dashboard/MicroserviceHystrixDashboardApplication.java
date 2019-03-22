package it.sella.sample.microservice.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableHystrixDashboard
@EnableEurekaClient
@SpringBootApplication
public class MicroserviceHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceHystrixDashboardApplication.class, args);
	}

}

