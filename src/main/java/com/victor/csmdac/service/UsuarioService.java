package com.victor.csmdac.service;

import com.victor.csmdac.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario salvar(Usuario usuario);

    List<Usuario> listarUsuarios();

    Optional<Usuario> buscarPorId(Long id);

    void removerPorId(Long id);

    boolean existePorId(Long id);
}
