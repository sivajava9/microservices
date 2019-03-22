package it.sella.sample.microservice.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MicroserviceBookApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(MicroserviceBookApplication.class);

	@RequestMapping(value = "/available")
	public String available() {
		LOGGER.debug("MicroserviceBookApplication>>available>>request");
		return "Spring in Action";
	}

	@RequestMapping(value = "/checked-out")
	public String checkedOut() {
		LOGGER.debug("MicroserviceBookApplication>>checkedOut>>request");
		return "Spring Boot in Action";
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBookApplication.class, args);
	}

}

