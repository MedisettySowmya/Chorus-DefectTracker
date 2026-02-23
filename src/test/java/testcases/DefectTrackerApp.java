package testcases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import DataModels.DefectData;
import Utilities.ExcelUtils;
import base.Browsers;
import pageobjects.DefectFormElements;

public class DefectTrackerApp extends Browsers {

	DefectFormElements defectElements = new DefectFormElements();

	// Actions initialized in the constructor

	@Test(priority = 2, groups = "defectTrackerAppLunch", dependsOnGroups = "login") // This test depends on the "login"
																						// group
	public void DefectTrackerAppLaunch() throws IOException {

		new Actions(driver);
		List<WebElement> noiframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Initial Number of iframes: " + noiframes.size());

		System.out.println("Launching defect tracker app");

		// Wait for the defect tracker link to be clickable and click on it
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_tracker_app"))))
				.click();
		System.out.println("Successfully launched the defect tracker app");

		// Locate the iframe and switch to it
		WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[1]"))); // Change
																												// XPath
																												// as
																												// needed

		driver.switchTo().frame(iframe);

		// Interact with project selection dropdown
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement projectSelectionDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Project']")));
		projectSelectionDropdown.click();
		projectSelectionDropdown.sendKeys("TUVSUD-MEDICI - TUV-SUD");

		WebElement selectProject = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[contains(text(),'TUVSUD-MEDICI - TUV-SUD')]")));

		selectProject.click();

		// Click on the "Add Defect" button to open the defect form
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add
		// Defect']"))) .click();

		// Initialize the WebElements for the defect form (from previous AddDefect
		// class)
		// initializeDefectFormElements();

		String propertiesFilePath = "C:\\Selenium with Java Learnings\\eclipse-workspace (1)\\eclipse-workspace\\ChorusAutomation\\src\\test\\resources\\config\\config.properties";

		try (FileReader filereader = new FileReader(propertiesFilePath)) {
			Properties properties = new Properties();
			properties.load(filereader);

			// Get Excel file path and sheet name from properties file
			String excelFilePath = properties.getProperty("excelFilePath");
			String sheetName = properties.getProperty("sheetName", "Sheet1");

			// Check if values are correct
			System.out.println("Excel File Path: " + excelFilePath);
			System.out.println("Sheet Name: " + sheetName);

			// Set Excel file and sheet
			ExcelUtils.setExcelFile(excelFilePath, sheetName);
			// Load Excel data to fill the defect form
			List<DefectData> defectDataList = ExcelUtils.getExcelDataAsDefects(); // Get defect data from Excel

			ExcelUtils.closeExcelFile(); // Close the Excel file after reading the data

			// Process each defect data and fill the form
			for (DefectData defectData : defectDataList) {
				try {
					List<WebElement> noiframess = driver.findElements(By.tagName("iframe"));
					System.out.println("Later Number of iframes: " + noiframess.size());
					Thread.sleep(5000); // or use WebDriverWait for confirmation element

					// Click "Add Defect" button (wait until clickable)
					WebElement addDefectBtn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
							.elementToBeClickable(By.xpath("//button[normalize-space()='Add Defect']")));
					addDefectBtn.click();

					//Optional: wait for loader overlay to disappear
					WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
					shortWait
							.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-overlay")));
					defectElements.initializeDefectFormElements();

					// Switch to iframe if form is inside one
					WebElement formIframe = driver.findElements(By.tagName("iframe")).size() > 0
							? driver.findElement(By.tagName("iframe"))
							: null;
					if (formIframe != null) {
						driver.switchTo().frame(formIframe);
					}
					Thread.sleep(5000);

					// Fill defect title
					defectElements.defectTitle.clear();
					defectElements.defectTitle.sendKeys(defectData.getDefectTitle());

					// Select sprint
					Select sprintDropdown = new Select(defectElements.sprintId);
					sprintDropdown.selectByVisibleText(defectData.getSprintId());

					// Select dev resource
					Select devResourceDropdown = new Select(defectElements.devResource);
					devResourceDropdown.selectByVisibleText(defectData.getDevResource());

					// Select Found by
					WebElement foundByDropdown = wait.until(ExpectedConditions.visibilityOf(driver.findElement(
							RelativeLocator.with(By.tagName("select")).below(defectElements.devResource))));
					// interact with dropdown

					Select select = new Select(foundByDropdown);
					select.selectByVisibleText(defectData.getFoundBy().trim());

					// Select defect category
					Select defectCategoryDropdown = new Select(defectElements.defectCategory);
					defectCategoryDropdown.selectByVisibleText(defectData.getDefectCategory());

					// Fill description
					defectElements.description.clear();
					defectElements.description.sendKeys(defectData.getDescription());

					// Select PBI ID
					Select pbiIdDropdown = new Select(defectElements.pbiId);
					pbiIdDropdown.selectByVisibleText(defectData.getPbiId());

					// Select defect type
					Select defectTypeDropdown = new Select(defectElements.defectType);
					defectTypeDropdown.selectByVisibleText(defectData.getDefectType());

					// Date seection
					String date = defectData.getFoundOn();
							//"15-December-2025";
					
					System.out.println(date);
							
					String[] dateParts = date.split(" ");
					String day = dateParts[0];      // "15"
					String monthFull = dateParts[1]; // "December"
					String year = dateParts[2];     // "2025"

					System.out.println("Day: " + day + ", Month: " + monthFull + ", Year: " + year);
					String ariaLabel = monthFull + " " + day + ", " + year; // "December 15, 2025"

					defectElements.foundOn.click();

					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-calendar")));

					driver.findElement(By.xpath("//button[@aria-label='Previous month']")).click();
					wait = new WebDriverWait(driver, Duration.ofSeconds(20));
					WebElement dayElement = wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//button[@aria-label='" + ariaLabel + "']")));
					try {
						dayElement.click();
					} catch (Exception e) {
						System.out.println("Error initializing DefectFormElements for defect: "
								+ defectData.getDefectTitle() + " - " + e.getMessage());
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", dayElement);
					}

					System.out.println("Selected date: " + ariaLabel);
					// foundOn.sendKeys(defectData.getFoundOn());

					// foundOn.sendKeys(defectData.getFoundOn());

					// Select fix provided by
					// Select fixProvidedByDropdown = new Select(fixProvidedBy);
					// fixProvidedByDropdown.selectByVisibleText(defectData.getFixProvidedBy());

					// Select assigned to
					Select assignedToDropdown = new Select(defectElements.assignedTo);
					assignedToDropdown.selectByVisibleText(defectData.getAssignedTo());

					// Select severity
					Select severityDropdown = new Select(defectElements.severity);
					severityDropdown.selectByVisibleText(defectData.getSeverity());

					// Select defect status
					// Select defectStatusDropdown = new Select(defectStatus);
					// defectStatusDropdown.selectByVisibleText(defectData.getDefectStatus());

					// Select environment
					Select environmentDropdown = new Select(defectElements.environment);
					environmentDropdown.selectByVisibleText(defectData.getEnvironment());

					// Select owned by
					// Select ownedByDropdown = new Select(ownedBy);
					// ownedByDropdown.selectByVisibleText(defectData.getOwnedBy());

					// Set fix provided on date
					// fixProvidedOn.clear();
					// fixProvidedOn.sendKeys(defectData.getFixProvidedOn());

					// wait.until(ExpectedConditions.elementToBeClickable(stepsToReproducebtn)).click();

					wait = new WebDriverWait(driver, Duration.ofSeconds(40));
					System.out.println("stepsToReproducebtn");

					WebElement stepsToReproducebtn = wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//span[contains(text(), 'Steps to Reproduce')]")));
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", stepsToReproducebtn);
					stepsToReproducebtn.click();

					wait = new WebDriverWait(driver, Duration.ofSeconds(30));
					System.out.println(defectData.getSteps());

					// Enter steps text
					WebElement stepsHere = driver.findElement(By.xpath("//div[@class='angular-editor-textarea']"));
					js.executeScript("arguments[0].scrollIntoView(true);", stepsHere);
					stepsHere.click();

					// Send text from Excel (getter method)
					String stepsText = defectData.getSteps();
					System.out.println("Steps text: " + stepsText);
					stepsHere.sendKeys(stepsText);

					// Enter Expected result text
					WebElement expectedResult = driver
							.findElement(By.xpath("//textarea[@formcontrolname='expectedResult']"));
					expectedResult.click();

					// send text from excel
					String expectedText = defectData.getExpectedResult();
					System.out.println("Expected Result :" + expectedText);
					expectedResult.sendKeys(expectedText);

					// Enter Expected result text
					WebElement actualResult = driver
							.findElement(By.xpath("//textarea[@placeholder='Enter Actual Result']"));
					actualResult.click();

					// send text from excel
					String actualText = defectData.getActualResult();
					System.out.println("Expected Result :" + actualText);
					actualResult.sendKeys(actualText);

					// Submit the form
					// addButton.click();

					// Optionally cancel the form
					// cancelButton.click();

					WebElement addButton = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("add"))));
					if (addButton.isEnabled()) {
						addButton.click();
						System.out.println("Add button clicked successfully!");
						Thread.sleep(10000);

					} else {
						System.out.println("Add button is not enabled yet.");

						WebElement cancelButton = wait.until(
								ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("cancel"))));
						// cancelButton.click();
					}

				} catch (Exception e) {
					System.out.println("Error initializing DefectFormElements for defect: "
							+ defectData.getDefectTitle() + " - " + e.getMessage());
				}
			}

		}

	}

	/*
	 * // Method to initialize defect form WebElements private void
	 * initializeDefectFormElements() { try { defectTitle = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "defect_title")))); sprintId = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("sprint_Id")))); devResource = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "dev_resource")))); foundBy = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("found_by")))); defectCategory = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "defect_category")))); description = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("description")))); pbiId =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("pbi_id")))); defectType = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("defect_type")))); foundOn = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("found_on")))); // fixProvidedBy = wait.until( //
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "fix_provided_by")))); assignedTo = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("assigned_to")))); severity = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("severity")))); defectStatus = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "defect_status")))); environment = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("environment")))); ownedBy = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("owned_by"))));
	 * 
	 * // fixProvidedOn = wait.until( //
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "fix_provided_on"))));
	 * 
	 * stepsToReproducebtn = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "steps_to_reproduce")))); //stepsToReproduceBody =
	 * wait.until(ExpectedConditions
	 * .visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "steps_to_reproduce_body")))); //steps =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("steps")))); stepsUploadBtn = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "steps_upload_btn")))); expectedResult = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "expected_result")))); expectedUploadBtn = wait.until(ExpectedConditions
	 * .visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "expected_upload_btn")))); actualResult = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "actual_result")))); actualUploadBtn = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "actual_upload_btn"))));
	 * 
	 * addButton =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("add")))); cancelButton = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("cancel"))));
	 * 
	 * } catch (Exception e) {
	 * System.out.println("Error initializing defect form elements: " +
	 * e.getMessage()); }
	 */
}
