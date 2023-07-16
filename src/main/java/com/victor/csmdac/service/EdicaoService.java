package com.victor.csmdac.service;

import com.victor.csmdac.entity.Edicao;

import java.util.List;
import java.util.Optional;

public interface EdicaoService {

    Edicao salvar(Edicao edicao);

    List<Edicao> listarEdicoes();

    List<Edicao> listarEdicoesPorIdEvento(Long eventoId);

    Optional<Edicao> buscarPorId(Long id);

    Optional<Edicao> buscarPorEdicaoEvento(Long edicaoId, Long eventoId);

    void removerPorId(Long id);

    boolean existePorId(Long id);
}
