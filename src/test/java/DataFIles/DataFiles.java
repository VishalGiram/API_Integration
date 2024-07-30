package DataFIles;

import net.datafaker.Faker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class DataFiles {

    public static void main(String[] args) {
        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Faker Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User ID");
        headerRow.createCell(1).setCellValue("Street 1");
        headerRow.createCell(2).setCellValue("Street 2");
        headerRow.createCell(3).setCellValue("Postal Code");
        headerRow.createCell(4).setCellValue("Company Name");
        headerRow.createCell(5).setCellValue("Legal Name");
        headerRow.createCell(6).setCellValue("Operating Name");
        headerRow.createCell(7).setCellValue("Facility Name");
        headerRow.createCell(8).setCellValue("Email Address");
        headerRow.createCell(9).setCellValue("Employer Field");
        headerRow.createCell(10).setCellValue("Job Title");
        headerRow.createCell(11).setCellValue("Fax Number");
        headerRow.createCell(12).setCellValue("DOB");

        Faker faker = new Faker();
        
        for (int i = 1; i <= 1000; i++) {
            String userId = faker.number().digits(3);
            String street1 = faker.address().streetAddress();
            String street2 = faker.address().secondaryAddress();
            String postalCode = faker.address().zipCode();
            String companyName = faker.company().name();
            String firstName = faker.name().firstName();
            String legalName = faker.company().name() + " Inc.";
            String operatingName = faker.company().name();
            String facilityName = faker.company().name() + " Facility";
            String emailAddress = firstName + "catch-all@eqanim-qa.wci-validate.org";
            String employerField = faker.company().profession();
            String jobTitle = faker.job().title();
            String faxNumber = faker.phoneNumber().phoneNumber();
            String DOB = faker.date().birthday("HH:MM:YYYY");

            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(firstName + userId);
            row.createCell(1).setCellValue(street1);
            row.createCell(2).setCellValue(street2);
            row.createCell(3).setCellValue(postalCode);
            row.createCell(4).setCellValue(companyName);
            row.createCell(5).setCellValue(legalName);
            row.createCell(6).setCellValue(operatingName);
            row.createCell(7).setCellValue(facilityName);
            row.createCell(8).setCellValue(emailAddress);
            row.createCell(9).setCellValue(employerField);
            row.createCell(10).setCellValue(jobTitle);
            row.createCell(11).setCellValue(faxNumber);
            System.out.println("batch "+userId);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream("FakerData.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
