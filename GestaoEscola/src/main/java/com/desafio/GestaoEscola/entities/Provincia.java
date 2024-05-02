package com.desafio.GestaoEscola.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProvincia;

    @Column(name = "nome",nullable = false)
    private String nome;


    @NotNull(message = "capital obrigatoria")
    @NotBlank(message = "capital obrigatoria")
    @NotEmpty(message = "Capital obrigatoria")
    @Column(name = "capital",nullable = false)
    private String capital;

}
