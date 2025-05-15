package com.fiap.mottuguard.controller;

import com.fiap.mottuguard.dto.UwbTagDTO;
import com.fiap.mottuguard.model.UwbTag;
import com.fiap.mottuguard.service.UwbTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uwbTag")
public class UwbTagController {

    @Autowired
    private UwbTagService uwbTagService;

    @GetMapping
    public ResponseEntity buscarTags(Pageable pageable) {
        Page<UwbTagDTO> page = uwbTagService.buscarUwbTags(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarUwbTag(@PathVariable Long id) {
        UwbTagDTO tag = uwbTagService.buscarTagPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @PostMapping
    public ResponseEntity criarUwbTag(@RequestBody UwbTagDTO tagDTO) {
        UwbTagDTO tagToAdd = uwbTagService.salvarUwbTag(tagDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagToAdd);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarTag(@PathVariable Long id, @RequestBody UwbTagDTO tagDTO) {
        UwbTagDTO tagToUpdate = uwbTagService.atualizarTag(id, tagDTO);

        return ResponseEntity.status(HttpStatus.OK).body(tagToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUwbTag(@PathVariable Long id) {
        uwbTagService.deletarTagUwbTag(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
