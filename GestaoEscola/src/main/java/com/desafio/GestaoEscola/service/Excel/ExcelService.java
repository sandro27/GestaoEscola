package com.desafio.GestaoEscola.service.Excel;


import com.desafio.GestaoEscola.dtos.escolaDTO;
import com.desafio.GestaoEscola.entities.Escola;
import com.desafio.GestaoEscola.entities.Provincia;
import com.desafio.GestaoEscola.repository.EscolaRepository;
import com.desafio.GestaoEscola.repository.ProvinciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExcelService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProvinciaRepository provinciarepo;

    @Autowired
    private EscolaRepository escolarepo;

    @Autowired
    private ExcelReader excelReader;




    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
    public List<Escola> getEscolaExcel(InputStream inputStream){
        List<escolaDTO> escolasdto=excelReader.read(inputStream);
        List<Escola> escolas = new ArrayList<>();
        for (escolaDTO escolaDTO :escolasdto ) {
            Escola escola = converToEscola(escolaDTO);
            if (pesquisarPorNomeAndProvincia(escola.getNome(),escolaDTO.getProvincia()).isEmpty())
                escolas.add(escola);
        }
        return escolas;
    }
    public  Escola converToEscola(escolaDTO escoladto)
    {
        Escola escola=new Escola();
        escola = modelMapper.map(escoladto,Escola.class);
        Optional<Provincia> provinciaOptional=provinciarepo.findByNome(escoladto.getProvincia());
        if (provinciaOptional.isPresent())
            escola.setProvincia(provinciaOptional.orElseThrow());
        return escola;
    }

    public Optional<Escola> pesquisarPorNomeAndProvincia(String nome, String provincia)
    {
        return escolarepo.findByNomeAndAndProvincia(nome,provincia);
    }
}
