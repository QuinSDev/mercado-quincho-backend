package com.mercado.quincho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mercado.quincho")
public class MercadoQuinchoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoQuinchoApplication.class, args);
	}

}
