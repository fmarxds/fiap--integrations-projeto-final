package br.com.fiap.integration.fiapintegrationsconsumer.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneDTO {

    private UUID droneId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double temperatura;
    private double umidade;
    private boolean ativarRastreamento;

}
