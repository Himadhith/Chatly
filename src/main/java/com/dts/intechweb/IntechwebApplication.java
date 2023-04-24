package com.dts.intechweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class IntechwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntechwebApplication.class, args);
	}

}
