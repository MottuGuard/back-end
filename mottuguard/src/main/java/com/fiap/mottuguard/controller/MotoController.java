package com.fiap.mottuguard.controller;

import com.fiap.mottuguard.dto.MotoDTO;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.service.MotoService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public Page<MotoDTO> buscarMotos(Pageable pageable) {
        Page<Moto> motos = motoService.listarMotos(pageable);

        return motos.map(moto -> {
            MotoDTO dto = new MotoDTO(moto);
            dto.add(linkTo(methodOn(MotoController.class)
                    .buscarMotoPorId(moto.getId())).withSelfRel());
            return dto;
        });
    }



    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> buscarMotoPorId(@PathVariable Long id) {
        Moto documento = motoService.buscarMotoPorId(id);
        MotoDTO dto = new MotoDTO(documento);
        dto.add(linkTo(methodOn(MotoController.class).buscarMotoPorId(id)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping()
    public ResponseEntity<MotoDTO> adicionarMoto(@RequestBody MotoDTO dto) {

        Moto motoToAdd = new Moto(dto.getId(), dto.getChassi(), dto.getPlaca(), dto.getModelo());
        motoToAdd = motoService.salvarMoto(motoToAdd);

        MotoDTO responseDto = new MotoDTO(motoToAdd);
        responseDto.add(linkTo(methodOn(MotoController.class).buscarMotoPorId(motoToAdd.getId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> atualizarMoto(@PathVariable Long id, @RequestBody MotoDTO dto) {
        Moto motoToUpdate = motoService.buscarMotoPorId(id);
        motoToUpdate.setPlaca(dto.getPlaca());
        motoToUpdate.setChassi(dto.getChassi());
        motoToUpdate.setModelo(dto.getModelo());

        motoService.atualizarMoto(id, motoToUpdate);

        MotoDTO responseDto = new MotoDTO(motoToUpdate);
        responseDto.add(linkTo(methodOn(MotoController.class).buscarMotoPorId(motoToUpdate.getId())).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarMoto(@PathVariable Long id) {
        motoService.deletarMoto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
