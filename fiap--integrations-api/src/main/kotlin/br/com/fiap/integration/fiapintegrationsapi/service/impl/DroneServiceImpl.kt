package br.com.fiap.integration.fiapintegrationsapi.service.impl

import br.com.fiap.integration.fiapintegrationsapi.model.DroneData
import br.com.fiap.integration.fiapintegrationsapi.service.DroneService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.text.DecimalFormat
import java.util.*
import kotlin.random.Random

@Service
class DroneServiceImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper,
) : DroneService {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun postDroneStatusData(droneData: DroneData) {
        val message = objectMapper.writeValueAsString(droneData)
        rabbitTemplate.convertAndSend("queue-drone-sensorial", message)
        logger.info("Mensagem enviada: $message")
    }

    @Scheduled(fixedDelay = 5000)
    fun postDroneStatusDataBot() {

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

}