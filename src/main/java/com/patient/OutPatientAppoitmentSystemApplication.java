package com.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class OutPatientAppoitmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutPatientAppoitmentSystemApplication.class, args);
	}

}
