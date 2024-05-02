package com.desafio.GestaoEscola.convert;

import com.desafio.GestaoEscola.dtos.escolaDTO;
import com.desafio.GestaoEscola.entities.Escola;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EscolaConvert {

    private ModelMapper modelMapper;

    public EscolaConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public escolaDTO convertEscolaToEscolaDTO(Escola escola) {

        escolaDTO escolaDto = modelMapper.map(escola, escolaDTO.class);

        return escolaDto;
    }

    public Escola convertEscolaDTOtoEscola(escolaDTO escoladto) {
        Escola escola = modelMapper.map(escoladto, Escola.class);
        return escola;
    }
}
