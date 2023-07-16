package com.victor.csmdac.http.controller;

import com.victor.csmdac.entity.Atividade;
import com.victor.csmdac.entity.Espaco;
import com.victor.csmdac.service.AtividadeService;
import com.victor.csmdac.service.EspacoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AtividadeController {

    static final String ACTIVITY_ERROR = "Atividade não encontrada";
    static final String PLACE_ERROR = "Espaço não encontrado";

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private EspacoService espacoService;

    // Rota para cadastrar uma atividade
    @PostMapping("/places/{placeId}/activities")
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade criarAtividade(@PathVariable("placeId") Long placeId, @Valid @RequestBody Atividade atividade) {
        Espaco espaco = espacoService.buscarPorId(placeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PLACE_ERROR));
        atividade.setEspaco(espaco);
        return atividadeService.salvar(atividade);
    }

    // Rota para listar todas atividades cadastradas
    @GetMapping("/activities")
    @ResponseStatus(HttpStatus.OK)
    public List<Atividade> listarAtividades() {
        return atividadeService.listarAtividades();
    }

    // Rotas para retornar uma atividade pelo ID
    @GetMapping("/activities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Atividade buscarAtividadePorId(@PathVariable("id") Long id) {
        return atividadeService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ACTIVITY_ERROR));
    }

    // Rota para retornar todas as atividades de um Espaço
    @GetMapping("/places/{placeId}/activities")
    public List<Atividade> listarAtividadesPorEspaco(@PathVariable Long placeId){
        return atividadeService.buscarPorEspaco(placeId);
    }

    // Rota para atualizar uma atividade
    @PutMapping("/activities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarAtividade(@Valid @PathVariable("id") Long id, @RequestBody Atividade novaAtividade) {
        atividadeService.buscarPorId(id)
                .map(atividade -> {
                    atividade.setNome(novaAtividade.getNome());
                    atividade.setTipo(novaAtividade.getTipo());
                    atividade.setDescricao(novaAtividade.getDescricao());
                    atividade.setData(novaAtividade.getData());
                    atividade.setHorario_inicial(novaAtividade.getHorario_inicial());
                    atividade.setHorario_final(novaAtividade.getHorario_final());
                    atividade.setEspaco(novaAtividade.getEspaco());
                    atividadeService.salvar(atividade);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ACTIVITY_ERROR));
    }

    // Rota para deletar uma atividade
    @DeleteMapping("/activities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable("id") Long id) {
        atividadeService.buscarPorId(id)
                .map(atividade -> {
                    atividadeService.removerPorId(atividade.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ACTIVITY_ERROR));
    }

    // Rota para deletar todas atividades de um Espaço
    @DeleteMapping("/places/{placeId}/activities")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorIdEspaco(@PathVariable("placeId") Long placeId) {
        espacoService.buscarPorId(placeId)
                .map(espaco -> {
                    atividadeService.removerPorIdEspaco(placeId);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PLACE_ERROR));
    }
}
