package br.com.fiap.integration.fiapintegrationsapi.service.impl

import br.com.fiap.integration.fiapintegrationsapi.model.DroneData
import br.com.fiap.integration.fiapintegrationsapi.service.DroneService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.text.DecimalFormat
import java.util.*
import javax.annotation.PostConstruct
import kotlin.random.Random

@Service
class DroneServiceImpl(
    private val amqpAdmin: AmqpAdmin,
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper,
) : DroneService {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val queueName: String = "queue-drone-sensorial"
    }

    override fun postDroneStatusData(droneData: DroneData) {
        val message = objectMapper.writeValueAsString(droneData)
        rabbitTemplate.convertAndSend(queueName, message)
        logger.info("-->MENSAGEM_ENVIADA: $message")
    }

    @Scheduled(fixedDelay = 10000)
    private fun postDroneStatusDataBot() {

        val droneData = DroneData(
            droneId = UUID.randomUUID().toString(),
            latitude = DecimalFormat("#.######").format(Random.nextDouble(-90.0, 90.0)).toDouble(),
            longitude = DecimalFormat("#.######").format(Random.nextDouble(-90.0, 90.0)).toDouble(),
            temperatura = DecimalFormat("#.##").format(Random.nextDouble(-25.0, 40.1)).toDouble(),
            umidade = DecimalFormat("#.##").format(Random.nextDouble(0.0, 100.1)).toDouble(),
            ativarRastreamento = listOf(true, false)[Random.nextInt(0, 2)]
        )

        this.postDroneStatusData(droneData)

    }

    @PostConstruct
    private fun createQueues() {
        if (amqpAdmin.getQueueInfo(queueName) == null) {
            amqpAdmin.declareQueue(Queue(queueName, true))
        }
    }

}