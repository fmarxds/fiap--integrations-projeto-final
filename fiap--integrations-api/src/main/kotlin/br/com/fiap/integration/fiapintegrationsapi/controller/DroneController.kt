package br.com.fiap.integration.fiapintegrationsapi.controller

import br.com.fiap.integration.fiapintegrationsapi.model.DroneData
import br.com.fiap.integration.fiapintegrationsapi.service.DroneService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/drones")
class DroneController(
    private val droneService: DroneService,
) {

    @PostMapping("/data")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun postDroneStatusData(
        @Valid @RequestBody droneData: DroneData
    ) {
        droneService.postDroneStatusData(droneData)
    }

}