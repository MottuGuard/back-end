package com.fiap.mottuguard.model;

import com.fiap.mottuguard.model.enums.ModeloMoto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O chassi é obrigatório")
    private String chassi;

    @NotBlank(message = "O chassi é obrigatório")
    private String placa;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ModeloMoto modelo;

}
