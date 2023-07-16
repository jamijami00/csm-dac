package com.victor.csmdac.repository;

import com.victor.csmdac.entity.Atividade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findAllByEspaco_Id(Long espacoId);
    @Transactional
    void deleteByEspaco_Id(Long id);

    @Transactional
    void deleteById(Long id);
}
