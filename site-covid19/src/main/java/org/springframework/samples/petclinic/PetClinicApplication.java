package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class PetClinicApplication {
	public static void main(String[] args) throws Throwable {
		SpringApplication.run(PetClinicApplication.class, args);
	}

}
