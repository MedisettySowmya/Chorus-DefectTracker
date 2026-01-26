package testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import DataModels.DefectData;
import Utilities.ExcelUtils;
import base.Browsers;
import pageobjects.DefectFormElements;

public class AddDefect extends Browsers {

	private DefectFormElements defectFormElements;

	public AddDefect() {
		defectFormElements = new DefectFormElements(); // Initialize page object class
	}

	//@Test(priority = 3, dependsOnGroups = "defectTrackerAppLunch")
	public void fillDefectForm() throws IOException {
		// Load properties to get Excel file path and sheet name
		//ReadPropertyFile.loadProperties();
		
		// Get file path and sheet name from properties file
		//String excelFilePath = ReadPropertyFile.getExcelFilePath();
		//String sheetName = ReadPropertyFile.getSheetName();

		// Set the Excel file path and sheet name in ExcelUtils
		//ExcelUtils.setExcelFile(excelFilePath, sheetName);
		List<DefectData> defectDataList = ExcelUtils.getExcelDataAsDefects(); // Get defect data from Excel

		// Close the Excel file after reading the data
		ExcelUtils.closeExcelFile();

		// Process each defect data and fill the form
		for (DefectData defectData : defectDataList) {
			try {
				// Fill defect title
				defectFormElements.defectTitle.clear();
				defectFormElements.defectTitle.sendKeys(defectData.getDefectTitle());

				// Select sprint
				Select sprintDropdown = new Select(defectFormElements.sprintId);
				sprintDropdown.selectByVisibleText(defectData.getSprintId());

				// Select dev resource
				Select devResourceDropdown = new Select(defectFormElements.devResource);
				devResourceDropdown.selectByVisibleText(defectData.getDevResource());

				// Select found by
				Select foundByDropdown = new Select(defectFormElements.foundBy);
				foundByDropdown.selectByVisibleText(defectData.getFoundBy());

				// Select defect category
				Select defectCategoryDropdown = new Select(defectFormElements.defectCategory);
				defectCategoryDropdown.selectByVisibleText(defectData.getDefectCategory());

				// Fill description
				defectFormElements.description.clear();
				defectFormElements.description.sendKeys(defectData.getDescription());

				// Select PBI ID
				Select pbiIdDropdown = new Select(defectFormElements.pbiId);
				pbiIdDropdown.selectByVisibleText(defectData.getPbiId());

				// Select defect type
				Select defectTypeDropdown = new Select(defectFormElements.defectType);
				defectTypeDropdown.selectByVisibleText(defectData.getDefectType());

				// Set found on date
				defectFormElements.foundOn.clear();
				defectFormElements.foundOn.sendKeys(defectData.getFoundOn());

				// Select fix provided by
				Select fixProvidedByDropdown = new Select(defectFormElements.fixProvidedBy);
				fixProvidedByDropdown.selectByVisibleText(defectData.getFixProvidedBy());

				// Select assigned to
				Select assignedToDropdown = new Select(defectFormElements.assignedTo);
				assignedToDropdown.selectByVisibleText(defectData.getAssignedTo());

				// Select severity
				Select severityDropdown = new Select(defectFormElements.severity);
				severityDropdown.selectByVisibleText(defectData.getSeverity());

				// Select defect status
				Select defectStatusDropdown = new Select(defectFormElements.defectStatus);
				defectStatusDropdown.selectByVisibleText(defectData.getDefectStatus());

				// Select environment
				Select environmentDropdown = new Select(defectFormElements.environment);
				environmentDropdown.selectByVisibleText(defectData.getEnvironment());

				// Select owned by
				Select ownedByDropdown = new Select(defectFormElements.ownedBy);
				ownedByDropdown.selectByVisibleText(defectData.getOwnedBy());

				// Set fix provided on date
				defectFormElements.fixProvidedOn.clear();
				defectFormElements.fixProvidedOn.sendKeys(defectData.getFixProvidedOn());

				// Enter steps to reproduce
				defectFormElements.steps.clear();
				defectFormElements.steps.sendKeys(defectData.getSteps());

				// Upload steps file if required
				defectFormElements.stepsUploadBtn.click();
				WebElement stepsFileInput = driver.findElement(By.xpath(locProp.getProperty("steps")));
				stepsFileInput.sendKeys(defectData.getSteps()); // Assuming stepsFilePath is passed here

				// Enter expected result
				defectFormElements.expectedResult.clear();
				defectFormElements.expectedResult.sendKeys(defectData.getExpectedResult());

				// Upload expected result file
				defectFormElements.expectedUploadBtn.click();
				WebElement expectedFileInput = driver.findElement(By.xpath(locProp.getProperty("expected_result")));
				expectedFileInput.sendKeys(defectData.getExpectedResult()); // Assuming expectedFilePath is passed here

				// Enter actual result
				defectFormElements.actualResult.clear();
				defectFormElements.actualResult.sendKeys(defectData.getActualResult());

				// Upload actual result file
				defectFormElements.actualUploadBtn.click();
				WebElement actualFileInput = driver.findElement(By.xpath(locProp.getProperty("actual_result")));
				actualFileInput.sendKeys(defectData.getActualResult()); // Assuming actualFilePath is passed here

				// Submit the form
				defectFormElements.addButton.click();

				// Cancel the form
				defectFormElements.cancelButton.click();

			} catch (Exception e) {
				System.out.println("Error initializing DefectFormElements for defect: " + defectData.getDefectTitle()
						+ " - " + e.getMessage());
			}
		}
	}
}
