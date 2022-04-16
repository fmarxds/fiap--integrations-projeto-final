package br.com.fiap.integration.fiapintegrationsconsumer.listener;

import br.com.fiap.integration.fiapintegrationsconsumer.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DroneMessageListener {

    private DroneRepository droneRepository;

    public void receiveMessage(Map<String, String> message) {
        System.out.println("Received < " + message + " >");

        System.out.println("Message processed...");
    }
}
