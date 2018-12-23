package com.simplebanking.sob;

import com.simplebanking.sob.Configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SobApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	}

}

