package it.sella.sample.microservice.image.samplemicroserviceimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication
public class SampleMicroserviceImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleMicroserviceImageApplication.class, args);
	}

}

