package com.fiap.mottuguard.dto;

import com.fiap.mottuguard.model.UwbTag;
import com.fiap.mottuguard.model.enums.ModeloMoto;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.model.enums.StatusMoto;
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
    private StatusMoto status;
    private ModeloMoto modelo;
    private UwbTag tag;

    public MotoDTO(Moto moto) {
        this.id = moto.getId();
        this.placa = moto.getPlaca();
        this.chassi = moto.getChassi();
        this.status = moto.getStatus();
        this.modelo = moto.getModelo();
        this.tag = moto.getTag();
    }

}
