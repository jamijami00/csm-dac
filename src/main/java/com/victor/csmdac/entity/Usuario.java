package com.victor.csmdac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @NotBlank(message = "login é obrigatório")
    @Size(min = 2,max = 20)
    private String login;

    @NotBlank(message = "email é obrigatório")
    @Email(message = "deve ter um e-mail válido")
    private String email;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotBlank(message = "afiliação é obrigatória")
    @Size(min = 2,max = 30)
    private String afiliacao;

    @OneToMany
    private List<Atividade> favoritos;

    public void addFavorito(Atividade atividade) {
        favoritos.add(atividade);
    }

    public void removeFavorito (Long atividadeId) {
        favoritos.removeIf(favorito -> favorito.getId() == atividadeId);
    }
}
