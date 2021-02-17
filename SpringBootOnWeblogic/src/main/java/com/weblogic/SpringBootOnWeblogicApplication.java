package com.weblogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SpringBootOnWeblogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOnWeblogicApplication.class, args);
	}

}