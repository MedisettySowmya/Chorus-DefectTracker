package testcases;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Correct for .xlsx files

public class Excel {

	public static void main(String[] args) throws IOException {

		// Create a new Excel workbook (for .xlsx file)
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a new sheet named "Sheet1"
		Sheet sheet = workbook.createSheet("Sheet1");

		// Create the first row (index 0)
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Name");
		header.createCell(1).setCellValue("Age");
		header.createCell(2).setCellValue("Gender");

		Row secondRow = sheet.createRow(1);
		secondRow.createCell(0).setCellValue("Sowmya");
		secondRow.createCell(1).setCellValue(24);
		secondRow.createCell(2).setCellValue("Female");

		// Create the first cell in the row (index 0)
		// Cell cell = row.createCell(0);

		// Set the cell value to "Name"
		// cell.setCellValue("Name");

		// Cell cell1= row.createCell(1);
		// cell1.setCellValue("Age");

		// Output file path for the new Excel file
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Sowmya Medisetty\\Documents\\StudentData.xlsx");

		// Write the workbook data to the file
		workbook.write(fos);

		// Close resources
		fos.close();
		workbook.close();

		System.out.println("New Excel file created successfully!");
	}
}
