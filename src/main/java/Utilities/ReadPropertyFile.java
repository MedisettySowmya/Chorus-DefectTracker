package Utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import DataModels.DefectData;

public class ReadPropertyFile {

	public static void main(String[] args) throws IOException {
		String propertiesFilePath ="C:\\Selenium with Java Learnings\\eclipse-workspace (1)\\eclipse-workspace\\ChorusAutomation\\src\\test\\resources\\config\\config.properties";
		// Use try-with-resources to ensure file reader is closed after use
		try (FileReader filereader = new FileReader(propertiesFilePath)) {

			Properties properties = new Properties();
			properties.load(filereader);
		
			// Get and print the properties from the file
			System.out.println("Browser: " + properties.getProperty("browser"));
			System.out.println("Chorus URL: " + properties.getProperty("ChorusURL"));

			String excelFilePath = properties.getProperty("excelFilePath");
			System.out.println(excelFilePath);
			String sheetName = properties.getProperty("sheetName", "Sheet1"); // Default to "Sheet1" if not specified
			System.out.println(sheetName);

			if ((excelFilePath == null || excelFilePath.isEmpty()) || (sheetName == null || sheetName.isEmpty())) {
				System.out.println("Excel file path/sheet name  is missing in the config file.");
				return;
			}
			ExcelUtils.setExcelFile(excelFilePath, sheetName);
			List<DefectData> defects = ExcelUtils.getExcelDataAsDefects();
			// Print the defect data to the console
			if (defects != null && !defects.isEmpty()) {
				System.out.println("\nDefect Data:");
				for (DefectData defect : defects) {
					System.out.println("---------------------------------------------------");
					System.out.println("Project: " + defect.getProject());
					System.out.println("Bug ID: " + defect.getBugId());
					System.out.println("Defect Title: " + defect.getDefectTitle());
					System.out.println("Sprint ID: " + defect.getSprintId());
					System.out.println("Dev Resource: " + defect.getDevResource());
					System.out.println("Found By: " + defect.getFoundBy());
					System.out.println("Defect Category: " + defect.getDefectCategory());
					System.out.println("Description: " + defect.getDescription());
					System.out.println("PBI ID: " + defect.getPbiId());
					System.out.println("Defect Type: " + defect.getDefectType());
					System.out.println("Found On: " + defect.getFoundOn());
					System.out.println("Fix Provided By: " + defect.getFixProvidedBy());
					System.out.println("Assigned To: " + defect.getAssignedTo());
					System.out.println("Severity: " + defect.getSeverity());
					System.out.println("Defect Status: " + defect.getDefectStatus());
					System.out.println("Environment: " + defect.getEnvironment());
					System.out.println("Owned By: " + defect.getOwnedBy());
					System.out.println("Fix Provided On: " + defect.getFixProvidedOn());
					System.out.println("Steps: " + defect.getSteps());
					System.out.println("Expected Result: " + defect.getExpectedResult());
					System.out.println("Actual Result: " + defect.getActualResult());
					System.out.println("---------------------------------------------------");
				}
			} else {
				System.out.println("No defect data found.");
			}

			// Optionally, you can close the Excel file after reading the data
			ExcelUtils.closeExcelFile();

		} catch (IOException e) {
			// Handle the case where the file is not found or cannot be read
			System.out.println("Error reading the properties file: " + e.getMessage());
		}
	}
}
