package com.desafio.GestaoEscola.dtos;

import com.desafio.GestaoEscola.entities.Provincia;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

@Data
public class escolaDTO {
    @Valid

    @NotNull(message = "nome é obrigatorio")
    @NotBlank(message = "nome é obrigatorio")
    private String nome;


    private String email;

    private int numSalas;

    @NotNull(message = "provincia é obrigatorio")
    @NotBlank(message = "provincia é obrigatorio")
    private String provincia;
}
