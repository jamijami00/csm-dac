package com.victor.csmdac.service.impl;

import com.victor.csmdac.entity.Edicao;
import com.victor.csmdac.service.EdicaoService;
import com.victor.csmdac.repository.EdicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EdicaoServiceImpl implements EdicaoService {

    private final EdicaoRepository edicaoRepository;

    public Edicao salvar(Edicao edicao) {
        return edicaoRepository.save(edicao);
    }

    public boolean existePorId(Long id) {
        return edicaoRepository.existsById(id);
    }

    public List<Edicao> listarEdicoes() {
        return edicaoRepository.findAll();
    }

    public List<Edicao> listarEdicoesPorIdEvento(Long eventoId) {
        return edicaoRepository.findAllByEvento_Id(eventoId);
    }

    public Optional<Edicao> buscarPorId(Long id) {
        return edicaoRepository.findById(id);
    }

    public Optional<Edicao> buscarPorEdicaoEvento(Long edicaoId, Long eventoId) {
        return edicaoRepository.findByIdAndAndEvento_Id(edicaoId, eventoId);
    }

    public void removerPorId(Long id) {
        edicaoRepository.deleteById(id);
    }
}
