package br.com.fiap.integration.fiapintegrationsconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double temperatura;
    private double umidade;
    private boolean ativarRastreamento;

}
