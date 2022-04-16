package br.com.fiap.integration.fiapintegrationsconsumer.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneDTO {

    private UUID droneId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double degree;
    private double umidity;
    private boolean isTracking;

}
