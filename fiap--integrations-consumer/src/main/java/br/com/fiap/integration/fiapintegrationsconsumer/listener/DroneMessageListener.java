package br.com.fiap.integration.fiapintegrationsconsumer.listener;

import br.com.fiap.integration.fiapintegrationsconsumer.model.DroneDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
@RequiredArgsConstructor
@Slf4j
public class DroneMessageListener {

    private Vector<DroneDTO> vector = new Vector();

    @RabbitListener(queues = {"queue-drone-sensorial"})
    public void receiveMessage(@Payload String fileBody) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        DroneDTO droneDTO = mapper.readValue(fileBody, DroneDTO.class);

        if (temperaturaPreocupante(droneDTO.getTemperatura()) ||
                umidadePreocupante(droneDTO.getUmidade()))
            vector.add(droneDTO);
    }

    private boolean temperaturaPreocupante(double temperatura) {
        return temperatura <= 0 || temperatura >= 35;
    }

    private boolean umidadePreocupante(double umidade) {
        return umidade < 15;
    }

    @Scheduled(fixedDelay = 60000)
    public synchronized void sendAlert() {
        if (!vector.isEmpty()) {
            log.warn("{} - Drone com variações preocupantes em seus dados: ", LocalDateTime.now());

            List<DroneDTO> lista = new ArrayList<>(vector);

            vector.forEach(this::alertDroneWithWarn);

            vector.removeAll(lista);
        }
    }

    public void alertDroneWithWarn(DroneDTO droneDTO) {
        log.warn("ID - {} | Temperatura - {} | Umidade - {} ",
                droneDTO.getDroneId(), droneDTO.getTemperatura(), droneDTO.getUmidade());
    }
}
