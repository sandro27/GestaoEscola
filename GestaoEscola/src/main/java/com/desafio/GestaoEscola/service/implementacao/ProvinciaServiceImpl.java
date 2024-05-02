package com.desafio.GestaoEscola.service.implementacao;

import com.desafio.GestaoEscola.dtos.PaisDTO;
import com.desafio.GestaoEscola.dtos.ProvinciaDTO;
import com.desafio.GestaoEscola.entities.Escola;
import com.desafio.GestaoEscola.entities.Provincia;
import com.desafio.GestaoEscola.repository.ProvinciaRepository;
import com.desafio.GestaoEscola.service.AbstractService;
import com.desafio.GestaoEscola.service.ProvinciaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProvinciaServiceImpl extends AbstractService<Provincia, UUID> implements ProvinciaService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Override
    public Provincia editar(UUID id, Provincia entidade) {
        entidade.setIdProvincia(id);
        return super.editar(id, entidade);
    }

    public PaisDTO criarProvincia(HashMap<String,PaisDTO> countries) throws JsonProcessingException {
        PaisDTO pais = countries.get("Angola");
        ArrayList<ProvinciaDTO> provinciaDtoList = pais.getProvincias();
        provinciaDtoList.forEach(provinciaDTO -> {
            Provincia provincia=  modelMapper.map(provinciaDTO,Provincia.class);
            if (provinciaRepository.findByNome(provincia.getNome()).isEmpty())
                super.criar(provincia);
            System.out.println(provincia);
        });
        return pais;
    }

    public Optional<Provincia> pesquisarPorNome(String nome)
    {
        return provinciaRepository.findByNome(nome);
    }

}
