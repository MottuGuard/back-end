package com.fiap.mottuguard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PositionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Moto moto;

    @NotNull(message = "O timestamp é obrigatório")
    private Date timestamp;

    @NotNull(message = "O x é obrigatório")
    private double x;

    @NotNull(message = "O y é obrigatório")
    private double y;

}
