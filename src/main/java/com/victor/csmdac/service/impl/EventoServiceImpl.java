package com.victor.csmdac.service.impl;

import com.victor.csmdac.entity.Evento;
import com.victor.csmdac.service.EventoService;
import com.victor.csmdac.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public Evento salvar(Evento evento) { return eventoRepository.save(evento);}

    public List<Evento> listarEventos(){
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(Long id){
        return eventoRepository.findById(id);
    }

    public void removerPorId(Long id){
        eventoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) { return eventoRepository.existsById(id); }
}
