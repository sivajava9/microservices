package it.sella.sample.microservice.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableEurekaClient
@EnableTurbine
@SpringBootApplication
public class SpringcloudMicroserviceTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudMicroserviceTurbineApplication.class, args);
	}

}

