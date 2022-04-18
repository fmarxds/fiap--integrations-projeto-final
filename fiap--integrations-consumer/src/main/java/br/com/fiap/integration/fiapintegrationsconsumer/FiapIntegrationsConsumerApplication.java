package br.com.fiap.integration.fiapintegrationsconsumer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@EnableScheduling
@SpringBootApplication
public class FiapIntegrationsConsumerApplication {

	@Autowired
	AmqpAdmin amqpAdmin;

	public static void main(String[] args) {
		SpringApplication.run(FiapIntegrationsConsumerApplication.class, args);
	}

	@PostConstruct
	public void criarFilas() {
		if (amqpAdmin.getQueueInfo("queue-drone-sensorial") == null) {
			amqpAdmin.declareQueue(new Queue("queue-drone-sensorial", true));
		}
	}

}
