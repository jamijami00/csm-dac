package com.victor.csmdac.repository;

import com.victor.csmdac.entity.Edicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EdicaoRepository extends JpaRepository<Edicao, Long> {

    Optional<Edicao> findByIdAndAndEvento_Id(Long edicaoId, Long eventoId);

    List<Edicao> findAllByEvento_Id(Long eventoId);
}
