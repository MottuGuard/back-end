package com.fiap.mottuguard.controller;

import com.fiap.mottuguard.dto.MotoDTO;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.model.enums.ModeloMoto;
import com.fiap.mottuguard.model.enums.StatusMoto;
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
    public ResponseEntity buscarMotos(Pageable pageable) {
        Page<MotoDTO> pageMotoDTO = motoService.listarMotos(pageable);

        return ResponseEntity.ok(pageMotoDTO);

    }

    @GetMapping("id/{id}")
    public ResponseEntity buscarMotoPorId(@PathVariable Long id) {
        MotoDTO moto = motoService.buscarMotoPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(moto);

    }

    @GetMapping("modelo/{modelo}")
    public ResponseEntity buscarMotoPorModelo(@PathVariable ModeloMoto modelo, Pageable pageable) {
        Page<MotoDTO> pageMotoDTO = motoService.buscarMotoPorModelo(modelo, pageable);

        return ResponseEntity.ok(pageMotoDTO);
    }

    @GetMapping("status/{status}")
    public ResponseEntity buscarMotoPorStatus(@PathVariable StatusMoto status, Pageable pageable) {
        Page<MotoDTO> pageMotoDTO = motoService.buscarMotosPorStatus(status, pageable);

        return ResponseEntity.ok(pageMotoDTO);
    }

    @PostMapping()
    public ResponseEntity adicionarMoto(@RequestBody MotoDTO dto) {
        MotoDTO motoToAdd = motoService.salvarMoto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoToAdd);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarMoto(@PathVariable Long id, @RequestBody MotoDTO dto) {
        MotoDTO responseDTO = motoService.atualizarMoto(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarMoto(@PathVariable Long id) {
        motoService.deletarMoto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
