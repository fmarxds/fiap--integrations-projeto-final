package br.com.fiap.integration.fiapintegrationsapi.config

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableRabbit
@Configuration
class RabbitMQConfig {

    @Bean
    fun amqpAdmin(connectionFactory: ConnectionFactory): AmqpAdmin? {
        return RabbitAdmin(connectionFactory)
    }

}