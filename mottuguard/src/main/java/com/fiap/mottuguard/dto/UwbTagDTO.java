package com.fiap.mottuguard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.model.UwbTag;
import com.fiap.mottuguard.model.enums.TagStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UwbTagDTO extends RepresentationModel<UwbTagDTO> {

    private Long id;
    private String Eui64;
    private TagStatus status;

    public UwbTagDTO(UwbTag uwbTag) {
        this.id = uwbTag.getId();
        this.Eui64 = uwbTag.getEui64();
        this.status = uwbTag.getStatus();
    }
}
