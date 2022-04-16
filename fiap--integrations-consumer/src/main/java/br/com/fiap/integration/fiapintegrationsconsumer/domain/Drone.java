package br.com.fiap.integration.fiapintegrationsconsumer.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drone {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private double degree;
    private double umidity;

    private Boolean isTracking;
}
