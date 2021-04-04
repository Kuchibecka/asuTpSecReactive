package ru.kuchibecka.asuTpSecReactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsuTpSecReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsuTpSecReactiveApplication.class, args);
	}

}
