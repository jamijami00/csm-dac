package com.victor.csmdac.http.controller;

import com.victor.csmdac.entity.Evento;
import com.victor.csmdac.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventoController {

    static final String EVENT_ERROR = "Evento não encontrado";

    @Autowired
    private EventoService eventoService;

    // Rota para cadastrar um evento
    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento salvar(@Valid @RequestBody Evento evento){
        return eventoService.salvar(evento);
    }

    // Rota para listar todos eventos cadastrados
    @GetMapping("/events")
    @ResponseStatus(HttpStatus.OK)
    public List<Evento> listarEventos(){
        return eventoService.listarEventos();
    }

    // Rota para retornar um evento pelo ID
    @GetMapping("/events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Evento buscarEventoPorId(@PathVariable("id") Long id){
        return eventoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EVENT_ERROR));
    }

    // Rota para deletar um evento cadastrado
    @DeleteMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEventoPorId(@PathVariable("id") Long id){
        eventoService.buscarPorId(id)
                .map(evento -> {
                    eventoService.removerPorId(evento.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EVENT_ERROR));
    }

    // Rota para atualizar um evento
    @PutMapping("/events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEvento(@Valid @PathVariable("id") Long id, @RequestBody Evento novoEvento){
        eventoService.buscarPorId(id)
                .map(evento -> {
                    evento.setNome(novoEvento.getNome());
                    evento.setSigla(novoEvento.getSigla());
                    evento.setDescricao(novoEvento.getDescricao());
                    eventoService.salvar(evento);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, EVENT_ERROR));
    }
}
