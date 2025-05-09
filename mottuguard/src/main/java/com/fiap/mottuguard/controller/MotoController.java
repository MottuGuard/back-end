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

    @GetMapping //ready
    public ResponseEntity buscarMotos(Pageable pageable) {
        try {
            Page<MotoDTO> pageMotoDTO = motoService.listarMotos(pageable);

            return ResponseEntity.ok(pageMotoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}") //ready
    public ResponseEntity buscarMotoPorId(@PathVariable Long id) {
        try{
            MotoDTO moto = motoService.buscarMotoPorId(id);

            return ResponseEntity.status(HttpStatus.OK).body(moto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping() //ready
    public ResponseEntity adicionarMoto(@RequestBody MotoDTO dto) {

        try{
            MotoDTO motoToAdd = motoService.salvarMoto(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(motoToAdd);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}") //ready
    public ResponseEntity atualizarMoto(@PathVariable Long id, @RequestBody MotoDTO dto) {
        try{
            MotoDTO responseDTO = motoService.atualizarMoto(id, dto);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("{id}") //ready
    public ResponseEntity deletarMoto(@PathVariable Long id) {
        try{
            motoService.deletarMoto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
