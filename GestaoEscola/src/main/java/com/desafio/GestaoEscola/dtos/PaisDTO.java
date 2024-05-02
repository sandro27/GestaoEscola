package com.desafio.GestaoEscola.dtos;

import com.desafio.GestaoEscola.entities.Provincia;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class PaisDTO {

    @JsonProperty("provincias")
    private ArrayList<ProvinciaDTO> provincias;
}
