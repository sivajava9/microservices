package it.sella.sample.microservice.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudFirstServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFirstServiceApplication.class, args);
	}

}
