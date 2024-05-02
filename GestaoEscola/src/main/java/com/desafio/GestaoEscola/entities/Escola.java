package com.desafio.GestaoEscola.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "Escola")
public class Escola {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEscola;

    @Valid
    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "email",nullable = false)
    private String email;


    @Column(name = "num_salas")
    private Integer numSalas;

    @ManyToOne
    private Provincia provincia;

}
