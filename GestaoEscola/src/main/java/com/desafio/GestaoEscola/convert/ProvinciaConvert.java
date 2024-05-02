package com.desafio.GestaoEscola.convert;

import com.desafio.GestaoEscola.dtos.ProvinciaDTO;
import com.desafio.GestaoEscola.dtos.escolaDTO;
import com.desafio.GestaoEscola.entities.Escola;
import com.desafio.GestaoEscola.entities.Provincia;
import jakarta.persistence.Convert;
import org.modelmapper.ModelMapper;

@Convert
public class ProvinciaConvert {
    private ModelMapper modelMapper;

    public ProvinciaConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ProvinciaDTO convertProvinciaToProvinciaDTO(Provincia provincia)
    {
        ProvinciaDTO provinciaDTO = modelMapper.map(provincia, ProvinciaDTO.class);
        return provinciaDTO;
    }

    public Provincia convertProvinciaDTOtoProvincia(ProvinciaDTO provinciaDTO) {
        Provincia provincia = modelMapper.map(provinciaDTO, Provincia.class);
        return provincia;
    }


}
