package com.victor.csmdac.http.controller;

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
public class EspacoController {

    static final String PLACE_ERROR = "Espaço não encontrado";

    @Autowired
    EspacoService espacoService;

    @Autowired
    private AtividadeService atividadeService;

    // Rota para cadastrar um espaço
    @PostMapping("/places")
    @ResponseStatus(HttpStatus.CREATED)
    public Espaco salvar(@Valid @RequestBody Espaco espaco){
        return espacoService.salvar(espaco);
    }

    // Rota para listar todos os espaços cadastrados
    @GetMapping("/places")
    @ResponseStatus(HttpStatus.OK)
    public List<Espaco> listarEspacos(){ return espacoService.listarEspacos(); }

    // Rota para retornar um espaço pelo ID
    @GetMapping("/places/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Espaco buscarEspacoPorId(@PathVariable("id") Long id){
        return espacoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PLACE_ERROR));
    }

    // Rota para deletar um espaço cadastrado
    @DeleteMapping("/places/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEspacoPorId(@PathVariable("id") Long id){
        if (atividadeService.existePorId(id)){
            atividadeService.removerPorId(id);
        }
        espacoService.buscarPorId(id)
                .map(espaco -> {
                    espacoService.removerPorId(espaco.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PLACE_ERROR));
    }

    // Rota para atualizar um espaço
    @PutMapping("/places/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarEspaco(@Valid @PathVariable("id") Long id, @RequestBody Espaco novoEspaco){
        espacoService.buscarPorId(id)
                .map(espaco -> {
                    espaco.setNome(novoEspaco.getNome());
                    espaco.setLocalizacao(novoEspaco.getLocalizacao());
                    espaco.setCapacidade(novoEspaco.getCapacidade());
                    espaco.setRecursos(novoEspaco.getRecursos());
                    espacoService.salvar(espaco);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PLACE_ERROR));
    }
}
