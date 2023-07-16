package com.victor.csmdac.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Edicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edicao_id")
    private Long id;

    private int numero;

    private int ano;

    private LocalDate data_inicial;

    private LocalDate data_final;

    private String cidade;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Usuario organizador;

    @OneToMany
    private List<Atividade> atividades;

    public void addAtividade(Atividade atividade) { atividades.add(atividade); }

    public void removeAtividade(Long atividadeId){
        atividades.removeIf(atividade -> atividade.getId() == atividadeId);
    }
}
