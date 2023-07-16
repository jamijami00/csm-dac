package com.victor.csmdac.service;

import com.victor.csmdac.entity.Espaco;

import java.util.List;
import java.util.Optional;

public interface EspacoService {

    Espaco salvar(Espaco espaco);

    List<Espaco> listarEspacos();

    Optional<Espaco> buscarPorId(Long id);

    void removerPorId(Long id);

    boolean existePorId(Long id);
}
