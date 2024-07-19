package org.vadarod.currencyratechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.vadarod.currencyratechecker.clients")
public class CurrencyRateCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateCheckerApplication.class, args);
	}

}
