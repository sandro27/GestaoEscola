package com.desafio.GestaoEscola.dtos;

import com.desafio.GestaoEscola.entities.Provincia;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ProvinciaDTO {


    @Valid

    private String nome;

    @NotNull(message = "capital obrigatoria")
    @NotBlank(message = "capital obrigatoria")
    private String capital;
}
