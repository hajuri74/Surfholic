package com.surfholicdb.surfholicdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SurfholicdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurfholicdbApplication.class, args);
	}

}
