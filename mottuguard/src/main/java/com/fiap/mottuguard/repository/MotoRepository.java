package com.fiap.mottuguard.repository;

import com.fiap.mottuguard.model.Moto;
import com.fiap.mottuguard.model.enums.ModeloMoto;
import com.fiap.mottuguard.model.enums.StatusMoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    @Query("SELECT m FROM Moto m WHERE m.modelo = :modelo")
    Page<Moto> buscarMotoPorModelo(@Param("modelo") ModeloMoto modelo, Pageable pageable);

    @Query(value = "SELECT * FROM Moto WHERE status = :status", nativeQuery = true)
    Page<Moto> buscarMotosPorStatus(@Param("status") StatusMoto status, Pageable pageable);

}
