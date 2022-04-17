package br.com.fiap.integration.fiapintegrationsapi.service.impl

import br.com.fiap.integration.fiapintegrationsapi.model.DroneData
import br.com.fiap.integration.fiapintegrationsapi.service.DroneService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class DroneServiceImpl(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper,
) : DroneService {

    override fun postDroneStatusData(droneData: DroneData) {
        val message = objectMapper.writeValueAsString(droneData)
        rabbitTemplate.convertAndSend("queue-drone-sensorial", message)
    }

}