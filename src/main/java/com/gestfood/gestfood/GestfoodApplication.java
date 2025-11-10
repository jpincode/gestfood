package com.gestfood.gestfood;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestfoodApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestfoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n-------------------------------- GESTFOOD APPLICATION --------------------------------\n");
		System.out.println("Aplicação 'GestFood' iniciada com sucesso!");
		System.out.println("\n-------------------------------- GESTFOOD APPLICATION --------------------------------\n");
	}

}
