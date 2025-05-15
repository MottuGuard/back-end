package com.fiap.mottuguard.service;

import com.fiap.mottuguard.controller.MotoController;
import com.fiap.mottuguard.dto.MotoDTO;
import com.fiap.mottuguard.exception.ResourceNotFoundException;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.model.UwbTag;
import com.fiap.mottuguard.model.enums.ModeloMoto;
import com.fiap.mottuguard.model.enums.StatusMoto;
import com.fiap.mottuguard.repository.MotoRepository;
import com.fiap.mottuguard.repository.UwbTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private UwbTagRepository tagRepository;

    public Page<MotoDTO> listarMotos(Pageable pageable) {

        Page<Moto> motos = motoRepository.findAll(pageable);
        if (motos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma moto encontrada");
        }

        Page<MotoDTO> dtoPage = motos.map(moto -> {
            MotoDTO dto = new MotoDTO(moto);
            dto.add(linkTo(methodOn(MotoController.class)
                    .buscarMotoPorId(moto.getId())).withSelfRel());
            return dto;
        });
        
        return dtoPage;
    }

    public MotoDTO buscarMotoPorId(Long id){
        Moto moto =  motoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Moto não encontrada"));
        MotoDTO dto = new MotoDTO(moto);
        dto.add(linkTo(methodOn(MotoController.class).buscarMotoPorId(id)).withSelfRel());
        return dto;
    }

    public Page<MotoDTO> buscarMotoPorModelo(ModeloMoto modelo, Pageable pageable) {
        Page<Moto> motos = motoRepository.buscarMotoPorModelo(modelo, pageable);
        if (motos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma moto encontrada");
        }

        Page<MotoDTO> dtoPage = motos.map(moto -> {
            MotoDTO dto = new MotoDTO(moto);
            dto.add(linkTo(methodOn(MotoController.class)
                    .buscarMotoPorId(moto.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    public Page<MotoDTO> buscarMotosPorStatus(StatusMoto status, Pageable pageable) {
        Page<Moto> motos = motoRepository.buscarMotosPorStatus(status, pageable);
        if (motos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma moto encontrada");
        }

        Page<MotoDTO> dtoPage = motos.map(moto -> {
            MotoDTO dto = new MotoDTO(moto);
            dto.add(linkTo(methodOn(MotoController.class)
                    .buscarMotoPorId(moto.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    public MotoDTO salvarMoto(MotoDTO motoDTO, Long id){
        UwbTag tag = tagRepository.findById(id).get();

        Moto motoToAdd = new Moto(motoDTO.getId(), motoDTO.getPlaca(), motoDTO.getChassi(), motoDTO.getStatus(), motoDTO.getModelo(), tag);
        motoRepository.save(motoToAdd);
        return new MotoDTO(motoToAdd);
    }

    public MotoDTO atualizarMoto(Long id, MotoDTO moto){
        Moto motoToUpdate = motoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));

        motoToUpdate.setModelo(moto.getModelo());
        motoToUpdate.setPlaca(moto.getPlaca());
        motoToUpdate.setChassi(moto.getChassi());
        motoToUpdate.setStatus(moto.getStatus());

        motoRepository.save(motoToUpdate);
        return new MotoDTO(motoToUpdate);

    }

    public void deletarMoto(Long id){
        if(!motoRepository.existsById(id)){
            throw new ResourceNotFoundException("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }

}
