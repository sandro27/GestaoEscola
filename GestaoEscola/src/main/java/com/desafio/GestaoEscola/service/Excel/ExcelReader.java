package com.desafio.GestaoEscola.service.Excel;

import com.desafio.GestaoEscola.dtos.escolaDTO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public interface ExcelReader {

    List<escolaDTO> read(InputStream inputStream);
}
