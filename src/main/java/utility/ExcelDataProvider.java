package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProvider {
    XSSFWorkbook wb;
    String Excelpath = System.getProperty("user.dir") + "//TestData//APITestData.xlsx";

    public ExcelDataProvider() throws IOException {
        File src = new File(Excelpath);
        FileInputStream file = new FileInputStream(src);
        wb = new XSSFWorkbook(file);
    }

    public String getStringData(String Sheetname, int row, int col) {
        return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
    }

    public double getNumericData(String Sheetname, int row, int col) {
        return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
    }

    // New method to fetch payload spanning multiple cells in a row
    public String getPayloadString(String Sheetname, int row, int startCol, int endCol) {
        StringBuilder payloadBuilder = new StringBuilder();
        Sheet sheet = wb.getSheet(Sheetname);
        Row r = sheet.getRow(row);

        for (int i = startCol; i <= endCol; i++) {
            Cell cell = r.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        payloadBuilder.append(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        payloadBuilder.append(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        payloadBuilder.append(cell.getBooleanCellValue());
                        break;
                    default:
                        break;
                }
            }
        }
        return payloadBuilder.toString();
    }
}

