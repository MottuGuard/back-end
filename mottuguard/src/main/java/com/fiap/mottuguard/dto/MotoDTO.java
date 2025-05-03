package com.fiap.mottuguard.dto;

import com.fiap.mottuguard.model.ModeloMoto;
import com.fiap.mottuguard.model.Moto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoDTO extends RepresentationModel<MotoDTO> {

    private Long id;
    private String placa;
    private String chassi;
    private ModeloMoto modelo;

    public MotoDTO(Moto moto) {
        this.id = moto.getId();
        this.placa = moto.getPlaca();
        this.chassi = moto.getChassi();
        this.modelo = moto.getModelo();
    }

}
