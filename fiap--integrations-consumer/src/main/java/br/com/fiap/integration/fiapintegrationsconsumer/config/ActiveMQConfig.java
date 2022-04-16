package br.com.fiap.integration.fiapintegrationsconsumer.config;

import br.com.fiap.integration.fiapintegrationsconsumer.listener.DroneMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ActiveMQConfig {

    @Value("${rabbitmq.queue}")
    private String DRONE_QUEUE;

    @Bean
    Queue queue() {
        return new Queue(DRONE_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DRONE_QUEUE);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DRONE_QUEUE);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(DroneMessageListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
