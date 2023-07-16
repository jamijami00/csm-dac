package com.victor.csmdac.entity;

import com.victor.csmdac.enums.TipoAtividade;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atividade_id")
    private long id;

    @NotBlank(message = "nome é obrigatório")
    @Size(min = 2,max = 30)
    private String nome;

    @NotNull(message = "tipo é obrigatório")
    private TipoAtividade tipo;

    @NotBlank(message = "descrição é obrigatória")
    @Size(min = 2,max = 250)
    private String descricao;

    @NotNull(message = "data é obrigatória")
    @FutureOrPresent(message = "data deve ser agora ou no futuro")
    private LocalDate data;

    @NotNull(message = "horário inicial é obrigatório")
    private LocalTime horario_inicial;

    @NotNull(message = "horário final é obrigatório")
    private LocalTime horario_final;

    @ManyToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;
}
