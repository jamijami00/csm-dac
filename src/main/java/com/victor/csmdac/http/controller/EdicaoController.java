package com.victor.csmdac.http.controller;

import com.victor.csmdac.entity.Edicao;
import com.victor.csmdac.entity.Evento;
import com.victor.csmdac.service.AtividadeService;
import com.victor.csmdac.service.EdicaoService;
import com.victor.csmdac.service.EventoService;
import com.victor.csmdac.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EdicaoController {

    static final String EDITION_ERROR = "Edição não encontrado";
    static final String EVENT_ERROR = "Evento não encontrado";
    static final String USER_ERROR = "Usuário não encontrado";
    static final String ACTIVITY_ERROR = "Atividade não encontrada";

    @Autowired
    private EdicaoService edicaoService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AtividadeService atividadeService;

    // Rota para cadastrar edição de um evento
    @PostMapping("/events/{eventId}/editions")
    @ResponseStatus(HttpStatus.CREATED)
    public Edicao criarEdicao(@PathVariable Long eventId, @Valid @RequestBody Edicao edicao){
        Evento evento = eventoService.buscarPorId(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EVENT_ERROR));
        edicao.setEvento(evento);
        return edicaoService.salvar(edicao);
    }

    // Rota para listar edições de um evento
    @GetMapping("/events/{eventId}/editions")
    @ResponseStatus(HttpStatus.OK)
    public List<Edicao> listarEdicoesDoEvento(@PathVariable Long eventId){
        return edicaoService.listarEdicoesPorIdEvento(eventId);
    }

    // Rota para listar uma edição de um evento
    @GetMapping("/events/{eventId}/{editionId}")
    @ResponseStatus(HttpStatus.OK)
    public Edicao buscarEdicaoPorId(@PathVariable Long eventId, @PathVariable Long editionId){
        return edicaoService.buscarPorEdicaoEvento(editionId, eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EVENT_ERROR));
    }

    // Rota para listar todas edições de todos eventos
    @GetMapping("/editions")
    @ResponseStatus(HttpStatus.OK)
    public List<Edicao> listarEdicoes(){ return edicaoService.listarEdicoes(); }

    // Rota para atualizar uma edição de um evento
    @PutMapping("/editions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEdicao(@Valid @PathVariable Long id, @RequestBody Edicao novaEdicao){
        edicaoService.buscarPorId(id)
                .map(edicao -> {
                    edicao.setNumero(novaEdicao.getNumero());
                    edicao.setAno(novaEdicao.getAno());
                    edicao.setData_inicial(novaEdicao.getData_inicial());
                    edicao.setData_final(novaEdicao.getData_final());
                    edicao.setCidade(novaEdicao.getCidade());
                    edicao.setEvento(novaEdicao.getEvento());
                    edicao.setOrganizador(novaEdicao.getOrganizador());
                    edicao.setAtividades(novaEdicao.getAtividades());
                    edicaoService.salvar(edicao);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EDITION_ERROR));
    }

    // Rota para adicionar uma atividade no evento
    @PutMapping("/editions/{editionId}/activities/{activityId}")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarAtividade(@PathVariable Long editionId, @PathVariable Long activityId){
        edicaoService.buscarPorId(editionId)
                .map(edicao -> {
                    atividadeService.buscarPorId(activityId)
                            .map(atividade -> {
                                edicao.addAtividade(atividade);
                                edicaoService.salvar(edicao);
                                return Void.TYPE;
                            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ACTIVITY_ERROR));
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EDITION_ERROR));
    }

    // Rota para deletar uma edição
    @DeleteMapping("/editions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEdicao(@PathVariable Long id){
        edicaoService.buscarPorId(id)
                .map(edicao -> {
                    edicaoService.removerPorId(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EDITION_ERROR));
    }

    // Rota para deletar uma atividade do evento
    @DeleteMapping("/editions/{editionId}/activities/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAtividade(@PathVariable Long editionId, @PathVariable Long activityId){
        edicaoService.buscarPorId(editionId)
                .map(edicao -> {
                    edicao.removeAtividade(activityId);
                    edicaoService.salvar(edicao);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EDITION_ERROR));
    }

    // Rota para adicionar um organizador a uma edição de um evento
    @PutMapping("/editions/{editionId}/organizer/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarOrganizador(@PathVariable Long editionId, @PathVariable Long userId){
        edicaoService.buscarPorId(editionId)
                .map(edicao -> {
                    usuarioService.buscarPorId(userId)
                            .map(usuario -> {
                                edicao.setOrganizador(usuario);
                                edicaoService.salvar(edicao);
                                return Void.TYPE;
                            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_ERROR));
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EDITION_ERROR));
    }

}
