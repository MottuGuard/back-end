package com.fiap.mottuguard.model;

import com.fiap.mottuguard.model.enums.TagStatus;
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
public class UwbTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Eui64 é obrigatório")
    private String eui64;

    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING)
    private TagStatus status;

}
