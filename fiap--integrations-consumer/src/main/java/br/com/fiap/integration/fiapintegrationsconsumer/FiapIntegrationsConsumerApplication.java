package br.com.fiap.integration.fiapintegrationsconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FiapIntegrationsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiapIntegrationsConsumerApplication.class, args);
	}

}
