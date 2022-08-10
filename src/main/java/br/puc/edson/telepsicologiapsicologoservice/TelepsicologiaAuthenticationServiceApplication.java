package br.puc.edson.telepsicologiapsicologoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TelepsicologiaAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelepsicologiaAuthenticationServiceApplication.class, args);
	}

}
