package com.victor.csmdac.service;

import com.victor.csmdac.entity.Evento;

import java.util.List;
import java.util.Optional;

public interface EventoService {

    Evento salvar(Evento evento);

    List<Evento> listarEventos();

    Optional<Evento> buscarPorId(Long id);

    void removerPorId(Long id);

    boolean existePorId(Long id);
}
