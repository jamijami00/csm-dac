package com.victor.csmdac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id")
    private long id;

    @NotBlank(message = "nome é obrigatório")
    @Size(min = 2,max = 30)
    private String nome;

    @NotBlank(message = "sigla é obrigatório")
    @Size(min = 2,max = 5)
    private String sigla;

    @NotBlank(message = "descrição é obrigatório")
    @Size(min = 2,max = 250)
    private String descricao;
}
