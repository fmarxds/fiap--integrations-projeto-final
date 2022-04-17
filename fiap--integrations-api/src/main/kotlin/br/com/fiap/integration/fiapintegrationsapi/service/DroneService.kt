package br.com.fiap.integration.fiapintegrationsapi.service

import br.com.fiap.integration.fiapintegrationsapi.model.DroneData

interface DroneService {

    fun postDroneStatusData(droneData: DroneData)

}