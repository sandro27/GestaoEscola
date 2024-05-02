package com.desafio.GestaoEscola.service.Excel;

import com.desafio.GestaoEscola.dtos.escolaDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ApachePOIExcelReader implements ExcelReader {


    @Override
    public List<escolaDTO> read(InputStream inputStream)
    {
        List<escolaDTO> escolaDTOList=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Escolas");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                escolaDTO escoladto = new escolaDTO();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> escoladto.setNome(cell.getStringCellValue());
                        case 1 -> escoladto.setEmail(cell.getStringCellValue());
                        case 2 -> escoladto.setNumSalas((int) cell.getNumericCellValue());
                        case 3 -> escoladto.setProvincia(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                escolaDTOList.add(escoladto);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return escolaDTOList;

    }

}
