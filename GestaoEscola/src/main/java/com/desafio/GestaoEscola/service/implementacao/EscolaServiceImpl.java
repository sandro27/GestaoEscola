package com.desafio.GestaoEscola.service.implementacao;

import com.desafio.GestaoEscola.entities.Escola;
import com.desafio.GestaoEscola.entities.Provincia;
import com.desafio.GestaoEscola.repository.EscolaRepository;
import com.desafio.GestaoEscola.repository.ProvinciaRepository;
import com.desafio.GestaoEscola.service.AbstractService;
import com.desafio.GestaoEscola.service.EscolaService;
import com.desafio.GestaoEscola.service.Excel.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class EscolaServiceImpl extends AbstractService<Escola, UUID> implements EscolaService {


    @Autowired
    private EscolaRepository escolaRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ExcelService excelService;
    @Override
    public Escola editar(UUID id, Escola entidade) {
        entidade.setIdEscola(id);
        return super.editar(id, entidade);
    }
    public List<Escola> salvarEscola(MultipartFile file)
    {
        if (excelService.isValidExcelFile(file)) {
            try {
                List<Escola> escolas = excelService.getEscolaExcel(file.getInputStream());
                return this.escolaRepository.saveAll(escolas);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
        return null;
    }
    public Escola pesquisarPorNome(String nome)
    {
        return escolaRepository.findByNome(nome);
    }


}
