package com.fiap.mottuguard.service;

import com.fiap.mottuguard.controller.MotoController;
import com.fiap.mottuguard.dto.MotoDTO;
import com.fiap.mottuguard.dto.UwbTagDTO;
import com.fiap.mottuguard.exception.ResourceNotFoundException;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.model.UwbTag;
import com.fiap.mottuguard.repository.UwbTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UwbTagService {

    @Autowired
    private UwbTagRepository repository;

    public Page<UwbTagDTO> buscarUwbTags(Pageable pageable) {
        Page<UwbTag> tags =  repository.findAll(pageable);
        if(tags.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma tag encontrada");
        }

        Page<UwbTagDTO> dtoPage = tags.map(tag -> {
            UwbTagDTO dto = new UwbTagDTO(tag);
            dto.add(linkTo(methodOn(MotoController.class)
                    .buscarMotoPorId(tag.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    public UwbTagDTO buscarTagPorId(Long id) {
        UwbTag tag =  repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Tag não encontrada"));
        UwbTagDTO dto = new UwbTagDTO(tag);
        dto.add(linkTo(methodOn(MotoController.class).buscarMotoPorId(id)).withSelfRel());
        return dto;
    }

    public UwbTagDTO salvarUwbTag(UwbTagDTO dto) {
        UwbTag tagToAdd = new UwbTag(dto.getId(), dto.getEui64(), dto.getStatus());
        repository.save(tagToAdd);
        return new UwbTagDTO(tagToAdd);
    }

    public UwbTagDTO atualizarTag(Long id, UwbTagDTO dto) {
        UwbTag tagToUpdate = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada"));
        tagToUpdate.setEui64(dto.getEui64());
        tagToUpdate.setStatus(dto.getStatus());

        repository.save(tagToUpdate);
        return new UwbTagDTO(tagToUpdate);
    }

    public void deletarTagUwbTag(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Tag não encontrada");
        }
        repository.deleteById(id);
    }
}
