package com.erp.inventariapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class InventariappApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventariappApplication.class, args);
	}

	@PostConstruct
	public void logStartupVars() {
		System.out.println("✅ PORT: " + System.getenv("PORT"));
		System.out.println("✅ DB_URL: " + System.getenv("DB_URL"));
	}	

}
