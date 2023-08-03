package com.anhvt.aptechmanagement.Utils;

import com.anhvt.aptechmanagement.Model.ObjectFromExcelScore;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static ArrayList<ObjectFromExcelScore> readExcel(String filePath) throws IOException {
        ArrayList<ObjectFromExcelScore> scores = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        // Bỏ qua dòng đầu tiên (chứa tên trường)
        if (((Iterator<?>) rowIterator).hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell codeCell = row.getCell(3);
            Cell scoreCell = row.getCell(5);

            String codeStudent = getStringValue(codeCell);
            int score;
            if (scoreCell.getCellType() == CellType.NUMERIC) {
                double numericScore = scoreCell.getNumericCellValue();
                score = (int) numericScore;
            } else {
                score = 101;
            }

            scores.add(new ObjectFromExcelScore(codeStudent, score));
        }

        workbook.close();
        fis.close();

        return scores;
    }
    private static String getStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    int numericValue = (int) cell.getNumericCellValue();
                    return String.valueOf(numericValue);
                }
            default:
                return "";
        }
    }
}
