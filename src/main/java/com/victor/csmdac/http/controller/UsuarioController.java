package com.victor.csmdac.http.controller;

import com.victor.csmdac.entity.Atividade;
import com.victor.csmdac.entity.Usuario;
import com.victor.csmdac.service.AtividadeService;
import com.victor.csmdac.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    static final String ERROR_USUARIO = "Usuário não encontrado";
    static final String ERROR_ATIVIDADE = "Atividade não encontrada";

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private UsuarioService usuarioService;

    // Rota para cadastrar um usuário
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@Valid @RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }

    // Rota para retornar todos usuários cadastrados
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    // Rota para retornar um usuário pelo ID
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id){
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_USUARIO));
    }

    // Rota para listar os favoritos de um usuário
    @GetMapping("/users/{id}/favorites")
    @ResponseStatus(HttpStatus.OK)
    public List<Atividade> listarFavoritos(@PathVariable("id") Long id){
        return usuarioService.buscarPorId(id).get().getFavoritos();
    }

    // Rota para deletar um usuário
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioPorId(@PathVariable("id") Long id){
        usuarioService.buscarPorId(id)
                .map(usuario -> {
                    usuarioService.removerPorId(usuario.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_USUARIO));
    }

    // Rota para desfavoritar uma atividade
    @DeleteMapping("/users/{userId}/favorites/{atividadeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarFavoritoPorId(@PathVariable("userId") Long userId, @PathVariable("atividadeId") Long atividadeId){
        usuarioService.buscarPorId(userId)
                .map(usuario -> {
                    usuario.removeFavorito(atividadeId);
                    usuarioService.salvar(usuario);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_USUARIO));
    }

    // Rota para atualizar um usuário
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarUsuario(@Valid @PathVariable("id") Long id, @RequestBody Usuario novoUsuario){
        usuarioService.buscarPorId(id)
                .map(usuario -> {
                    usuario.setLogin(novoUsuario.getLogin());
                    usuario.setEmail(novoUsuario.getEmail());
                    usuario.setNome(novoUsuario.getNome());
                    usuario.setAfiliacao(novoUsuario.getAfiliacao());
                    usuarioService.salvar(usuario);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_USUARIO));
    }

    // Rota para adicionar favoritar uma atividade
    @PutMapping("/users/{userId}/favorites/{atividadeId}")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarFavorito(@Valid @PathVariable("userId") Long userId, @PathVariable("atividadeId") Long atividadeId){
        usuarioService.buscarPorId(userId)
                .map(usuario -> {
                    atividadeService.buscarPorId(atividadeId)
                            .map(atividade -> {
                                usuario.addFavorito(atividade);
                                usuarioService.salvar(usuario);
                                return Void.TYPE;
                            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_ATIVIDADE));
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_USUARIO));
    }

}
