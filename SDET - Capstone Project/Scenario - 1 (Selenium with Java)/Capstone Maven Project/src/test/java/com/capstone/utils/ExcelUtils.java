package com.capstone.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class ExcelUtils {

    public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(file);

        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Skip the header row
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                if(cell != null){
                    data[i][j] = cell.toString();
                }

            }
        }

        workbook.close();
        file.close();
        return data;
    }
}