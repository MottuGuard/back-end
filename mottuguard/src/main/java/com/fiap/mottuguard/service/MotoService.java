package com.fiap.mottuguard.service;

import com.fiap.mottuguard.dto.MotoDTO;
import com.fiap.mottuguard.exception.ResourceNotFoundException;
import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public Page<Moto> listarMotos(Pageable pageable) {
        Page<Moto> motos = motoRepository.findAll(pageable);
        if (motos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma moto encontrada");
        }
        return motos;
    }

    public Moto buscarMotoPorId(Long id){
        return motoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Moto não encontrada"));
    }

    public Moto salvarMoto(Moto moto){
        return motoRepository.save(moto);
    }

    public Moto atualizarMoto(Long id, Moto moto){
        Moto motoAtualizada = motoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));

        motoAtualizada.setModelo(moto.getModelo());
        motoAtualizada.setPlaca(moto.getPlaca());
        motoAtualizada.setChassi(moto.getChassi());

        return motoRepository.save(motoAtualizada);

    }

    public void deletarMoto(Long id){
        if(!motoRepository.existsById(id)){
            throw new ResourceNotFoundException("Moto não encontrada");
        }
        motoRepository.deleteById(id);
    }

}
