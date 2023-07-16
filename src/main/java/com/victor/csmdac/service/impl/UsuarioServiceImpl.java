package com.victor.csmdac.service.impl;

import com.victor.csmdac.entity.Usuario;
import com.victor.csmdac.service.UsuarioService;
import com.victor.csmdac.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public void removerPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public boolean existePorId(Long id) { return usuarioRepository.existsById(id); }

}
