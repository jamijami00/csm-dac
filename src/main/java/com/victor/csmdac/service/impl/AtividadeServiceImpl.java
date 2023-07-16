package com.victor.csmdac.service.impl;

import com.victor.csmdac.entity.Atividade;
import com.victor.csmdac.service.AtividadeService;
import com.victor.csmdac.repository.AtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtividadeServiceImpl implements AtividadeService {

    private final AtividadeRepository atividadeRepository;

    public Atividade salvar(Atividade atividade) { return atividadeRepository.save(atividade);}

    public List<Atividade> listarAtividades(){
        return atividadeRepository.findAll();
    }

    public List<Atividade> buscarPorEspaco(Long espacoId) { return atividadeRepository.findAllByEspaco_Id(espacoId);}

    public Optional<Atividade> buscarPorId(Long id){
        return atividadeRepository.findById(id);
    }

    public void removerPorId(Long id){
        atividadeRepository.deleteById(id);
    }

    public void removerPorIdEspaco(Long espacoId) {
        atividadeRepository.deleteByEspaco_Id(espacoId);
    }

    public boolean existePorId(Long id) { return atividadeRepository.existsById(id); }
}
