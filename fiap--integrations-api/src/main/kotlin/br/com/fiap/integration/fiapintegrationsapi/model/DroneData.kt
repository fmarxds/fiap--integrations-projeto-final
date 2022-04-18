package br.com.fiap.integration.fiapintegrationsapi.model

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class DroneData(

    @field:NotBlank(message = "O droneId deve ser informado")
    val droneId: String,
    val latitude: Double,
    val longitude: Double,
    @field:Min(value = -25, message = "A temperatura deve ser no mínimo -25°C")
    @field:Max(value = 40, message = "A temperatura deve ser no máximo 40°C")
    val temperatura: Double,
    @field:Min(value = 0, message = "A umidade deve ser no mínimo 0%")
    @field:Max(value = 100, message = "A umidade deve ser no máximo 100%")
    val umidade: Double,
    val ativarRastreamento: Boolean,

)
