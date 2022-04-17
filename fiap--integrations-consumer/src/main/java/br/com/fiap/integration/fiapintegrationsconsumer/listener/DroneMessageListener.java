package br.com.fiap.integration.fiapintegrationsconsumer.listener;

import br.com.fiap.integration.fiapintegrationsconsumer.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class DroneMessageListener {

    private DroneRepository droneRepository;

    @RabbitListener(queues = {"queue-drone-sensorial"})
    public void receiveMessage(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
    }

}
