package com.fiap.mottuguard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UwbMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O timestamp é obrigatório")
    private Date timestamp;

    @NotNull(message = "A distancia é obrigatório")
    private double distance;

    @NotNull(message = "O rssi é obrigatório")
    private double rssi;

    @OneToOne(fetch = FetchType.EAGER)
    private UwbAnchor uwbAnchor;

    @OneToOne(fetch = FetchType.EAGER)
    private UwbTag uwbTag;

}
