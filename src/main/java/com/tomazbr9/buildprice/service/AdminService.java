package com.tomazbr9.buildprice.service;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class AdminService {

    public void popularWithSinapi(MultipartFile file) throws IOException {

        if(file.isEmpty()){
            throw new RuntimeException("Arquivo vazio");
        }

        try(InputStream is = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(is)
        ) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for(Row row : sheet){
                if(row.getRowNum() == 0) continue;

                String value = formatter.formatCellValue(row.getCell(0));
                System.out.println(value);
            }

        }






    }
}
