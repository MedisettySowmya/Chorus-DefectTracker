package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DataModels.DefectData;

public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksheet;

	public static void setExcelFile(String filePath, String sheetName) throws IOException {
		try (FileInputStream excelFile = new FileInputStream(filePath)) {
			workbook = new XSSFWorkbook(excelFile);
			worksheet = workbook.getSheet(sheetName);
			if (worksheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}
		} catch (IOException e) {
			throw new IOException("Error reading the Excel file: " + e.getMessage());
		}
	}

	public static List<DefectData> getExcelDataAsDefects() {
		List<DefectData> defects = new ArrayList<>();

		// Iterate through the rows (skip header)
		int lastRowNum = worksheet.getLastRowNum();
		int rowCount = worksheet.getPhysicalNumberOfRows();
		System.out.println("Last row number with data: " + rowCount);
		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
			XSSFRow row = worksheet.getRow(rowNum);
			if (row == null)
			{
				continue;
			}
			DefectData defect = new DefectData();

			defect.setProject(getCellData(row.getCell(0)));
			defect.setBugId(getCellData(row.getCell(1)));
			defect.setDefectTitle(getCellData(row.getCell(2)));
			defect.setSprintId(getCellData(row.getCell(3)));
			defect.setDevResource(getCellData(row.getCell(4)));
			defect.setFoundBy(getCellData(row.getCell(5)));
			defect.setDefectCategory(getCellData(row.getCell(6)));
			defect.setDescription(getCellData(row.getCell(7)));
			defect.setPbiId(getCellData(row.getCell(8)));
			defect.setDefectType(getCellData(row.getCell(9)));
			//defect.setFoundOn(getCellData(row.getCell(10)));
			defect.setFoundOn(getCellData(row.getCell(10)));

			defect.setFixProvidedBy(getCellData(row.getCell(11)));
			defect.setAssignedTo(getCellData(row.getCell(12)));
			defect.setSeverity(getCellData(row.getCell(13)));
			defect.setDefectStatus(getCellData(row.getCell(14)));
			defect.setEnvironment(getCellData(row.getCell(15)));
			defect.setOwnedBy(getCellData(row.getCell(16)));
			defect.setFixProvidedOn(getCellData(row.getCell(17)));
			defect.setSteps(getCellData(row.getCell(18)));
			defect.setExpectedResult(getCellData(row.getCell(19)));
			defect.setActualResult(getCellData(row.getCell(20)));

			defects.add(defect);
		}

		return defects; // Return the list of Defect objects
	}

	


	private static String getCellData(XSSFCell cell) {
	    if (cell == null) {
	        return "";
	    }

	    String cellData = "";

	    switch (cell.getCellType()) {
	        case STRING:
	            cellData = cell.getStringCellValue();
	            break;

	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	            	  Date date = cell.getDateCellValue();
	                  // Format: 12 December 2025
	                  SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
	                  cellData = sdf.format(date);
	            } else {
	                double numericValue = cell.getNumericCellValue();
	                if (numericValue == (long) numericValue) {
	                    cellData = String.valueOf((long) numericValue);
	                } else {
	                    cellData = String.valueOf(numericValue);
	                }
	            }
	            break;

	        case BOOLEAN:
	            cellData = String.valueOf(cell.getBooleanCellValue());
	            break;

	        case FORMULA:
	            // Optional: evaluate formula
	            try {
	                cellData = String.valueOf(cell.getNumericCellValue());
	            } catch (Exception e) {
	                cellData = cell.getCellFormula();
	            }
	            break;

	        case BLANK:
	        default:
	            cellData = "";
	            break;
	    }

	    return cellData;
	}
	public static void closeExcelFile() throws IOException {
		if (workbook != null) {
			workbook.close();
		}
	}
}
