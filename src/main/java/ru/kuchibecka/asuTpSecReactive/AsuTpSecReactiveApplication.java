package ru.kuchibecka.asuTpSecReactive;

import org.neo4j.springframework.data.repository.config.EnableNeo4jRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication
@EnableAsync
@Configuration				// todo: ???	https://spring-projects.ru/guides/accessing-data-neo4j/
@EnableNeo4jRepositories	// todo: ???
public class AsuTpSecReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsuTpSecReactiveApplication.class, args);
	}

}
