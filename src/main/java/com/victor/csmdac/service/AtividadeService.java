package com.victor.csmdac.service;

import com.victor.csmdac.entity.Atividade;

import java.util.List;
import java.util.Optional;

public interface AtividadeService {

    Atividade salvar(Atividade atividade);

    List<Atividade> listarAtividades();

    Optional<Atividade> buscarPorId(Long id);

    void removerPorId(Long id);

    void removerPorIdEspaco(Long espacoId);

    boolean existePorId(Long id);

    List<Atividade> buscarPorEspaco(Long placeId);
}
