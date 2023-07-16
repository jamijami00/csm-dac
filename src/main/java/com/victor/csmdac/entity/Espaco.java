package com.victor.csmdac.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "espaco_id")
    private Long id;

    @NotBlank(message = "nome é obrigatório")
    @Size(min = 2,max = 30)
    private String nome;

    @NotBlank(message = "localizacao é obrigatória")
    @Size(min = 2,max = 30)
    private String localizacao;

    @NotNull(message = "capacidade é obrigatória")
    @Positive(message = "capacidade deve ser um número positivo")
    private int capacidade;

    @ElementCollection
    @Column(name = "recurso")
    private String[] recursos;
}
