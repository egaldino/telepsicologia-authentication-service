package br.puc.edson.telepsicologiapsicologoservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages =
        "br.puc.edson.telepsicologiapsicologoservice.repository.psichologist",
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {

}