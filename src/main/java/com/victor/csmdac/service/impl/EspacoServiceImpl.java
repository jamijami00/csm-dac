package com.victor.csmdac.service.impl;

import com.victor.csmdac.entity.Espaco;
import com.victor.csmdac.repository.EspacoRepository;
import com.victor.csmdac.service.EspacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspacoServiceImpl implements EspacoService {

    private final EspacoRepository espacoRepository;

    public Espaco salvar(Espaco espaco) { return espacoRepository.save(espaco);}

    public List<Espaco> listarEspacos(){
        return espacoRepository.findAll();
    }

    public Optional<Espaco> buscarPorId(Long id){
        return espacoRepository.findById(id);
    }

    public void removerPorId(Long id){
        espacoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) { return espacoRepository.existsById(id); }
}
