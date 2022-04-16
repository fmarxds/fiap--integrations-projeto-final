package br.com.fiap.integration.fiapintegrationsconsumer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sound.midi.Receiver;

@Configuration
@RequiredArgsConstructor
public class ActiveMQConfig {

    @Value("${rabbitmq.queueName}")
    private final String queueName;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @RabbitListener(queues = "${rabbitmq.queueName}")
    public void listen(String in) {
        System.out.println("message - " + in);
    }
}
