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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import DataModels.DefectData;
import Utilities.ExcelUtils;
import base.Browsers;
import pageobjects.DefectFormElements;

public class DefectTrackerAppScroller extends Browsers {

	// WebElements for defect form fields
	public WebElement defectTitle;
	public WebElement sprintId;
	public WebElement devResource;

	// WebElements for additional defect fields
	public WebElement foundBy;
	public WebElement defectCategory;
	public WebElement description;
	public WebElement pbiId;
	public WebElement defectType;
	public WebElement foundOn;
	public WebElement fixProvidedBy;
	public WebElement assignedTo;
	public WebElement severity;
	public WebElement defectStatus;
	public WebElement environment;
	public WebElement ownedBy;
	public WebElement fixProvidedOn;

	// WebElements for steps and expected/actual result sections
	public WebElement stepsToReproducebtn;
	public WebElement stepsToReproduceBody;
	public WebElement steps;
	public WebElement stepsUploadBtn;
	public WebElement expectedResult;
	public WebElement expectedUploadBtn;
	public WebElement actualResult;
	public WebElement actualUploadBtn;

	// WebElements for buttons
	public WebElement addButton;
	public WebElement cancelButton;
	public Actions actions;
	DefectFormElements defectElements = new DefectFormElements();

	// Actions initialized in the constructor

	@Test(priority = 2, groups = "defectTrackerAppLunch", dependsOnGroups = "login") // This test depends on the "login"
																						// group
	public void DefectTrackerAppLaunch() throws IOException {

		new Actions(driver);
		List<WebElement> noiframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of iframes: " + noiframes.size());

		System.out.println("Launching defect tracker app");

		// Wait for the defect tracker link to be clickable and click on it
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_tracker_app"))))
				.click();
		System.out.println("Successfully launched the defect tracker app");

		// Locate the iframe and switch to it
		WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("applicationId"))); // Change
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

		// List<WebElement> ProjectNames =
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class=\"mat-option-text\"]")));

		// for(WebElement )
		// Select a project from the dropdown
		// Select projectSelection = new Select(projectSelectionDropdown);
		// projectSelection.selectByVisibleText(locProp.getProperty("ProjectName"));

		System.out.println("Project selected");
		// WebElement body = driver.findElement(By.tagName("body"));
		// body.click();

		// Click on the "Add Defect" button to open the defect form
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Add Defect']"))).click();
		System.out.println("click on add defect button");

		// Initialize the WebElements for the defect form (from previous AddDefect
		// class)
		defectElements.initializeDefectFormElements();

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
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));

					// Example input: 15-Dec-25 or 15-Dec-2025
					String date = "15-December-2025";
							//defectData.getFoundOn();
					System.out.println(date);
					String[] dateParts = date.split("-");
					String day = dateParts[0];
					String month = dateParts[1];
					String year = dateParts[2];
					String ariaLabel = month + " " + day + ", " + year;

					if (year.length() == 2) {
						year = "20" + year;// Convert "24" to "2024"
					}
					defectElements.foundOn.click();
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-calendar")));

					driver.findElement(By.xpath("//button[@aria-label='Previous month']")).click();
					 wait = new WebDriverWait(driver, Duration.ofSeconds(20));
					 WebElement dayElement = wait.until(
					     ExpectedConditions.elementToBeClickable(
					         By.xpath("//button[@aria-label='" + ariaLabel + "']")
					     )
					 );
					 try {
					     dayElement.click();
					 } catch (Exception e) {
						 System.out.println("Error initializing DefectFormElements for defect: "
									+ defectData.getDefectTitle() + " - " + e.getMessage());
					     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dayElement);
					 }

					 System.out.println("Selected date: " + ariaLabel);
					/*WebElement dateElement = wait.until(
					    ExpectedConditions.elementToBeClickable(
					        By.xpath("//mat-month-view//button[.//span[text()='" + day + "']]")
					    )
					);

					dateElement.click();
					System.out.println("Selected day: " + day);
					//driver.findElement(By.xpath("//div[normalize-space()='" + day + "']")).click();

					// Select Dev Resource dropdown
					// Select dev resource
					/*
					 * Select devResourceDropdown = new Select(devResource);
					 * devResourceDropdown.selectByVisibleText(defectData.getDevResource());
					 * 
					 * // Select Found by WebElement foundByDropdown = wait.until(ExpectedConditions
					 * .visibilityOf(driver.findElement(RelativeLocator.with(By.tagName("select")).
					 * below(devResource)))); // interact with dropdown
					 * 
					 * Select select = new Select(foundByDropdown);
					 * select.selectByVisibleText(defectData.getFoundBy().trim());
					 */

					/*
					 * System.out.println("stepsToReproducebtn");
					 * 
					 * WebElement stepsToReproducebtn = wait.until(ExpectedConditions
					 * .elementToBeClickable(By.
					 * xpath("//span[contains(text(), 'Steps to Reproduce')]"))); JavascriptExecutor
					 * js = (JavascriptExecutor) driver;
					 * js.executeScript("arguments[0].scrollIntoView(true);", stepsToReproducebtn);
					 * stepsToReproducebtn.click();
					 * 
					 * wait = new WebDriverWait(driver, Duration.ofSeconds(30));
					 * System.out.println(defectData.getSteps());
					 * 
					 * // Enter steps text WebElement stepsHere = driver
					 * .findElement(By.xpath("//div[@class='angular-editor-textarea']"));
					 * js.executeScript("arguments[0].scrollIntoView(true);", stepsHere);
					 * stepsHere.click();
					 * 
					 * 
					 * // ✅ Send text from Excel (getter method) String stepsText =
					 * defectData.getSteps(); System.out.println("Steps text: " + stepsText);
					 * stepsHere.sendKeys(stepsText);
					 * 
					 * // Enter Expected result text WebElement expectedResult =
					 * driver.findElement(By.xpath("//textarea[@formcontrolname='expectedResult']"))
					 * ; expectedResult.click();
					 * 
					 * // send text from excel String expectedText = defectData.getExpectedResult();
					 * System.out.println("Expected Result :" + expectedText);
					 * expectedResult.sendKeys(expectedText);
					 * 
					 * // Enter Expected result text WebElement actualResult =
					 * driver.findElement(By.xpath("//textarea[@placeholder='Enter Actual Result']")
					 * ); actualResult.click();
					 * 
					 * // send text from excel String actualText = defectData.getActualResult();
					 * System.out.println("Expected Result :" + actualText);
					 * actualResult.sendKeys(actualText);
					 */

				} catch (Exception e) {
					System.out.println("Error initializing DefectFormElements for defect: "
							+ defectData.getDefectTitle() + " - " + e.getMessage());
				}
			}

		}

	}

	// Method to initialize defect form WebElements
	/*
	 * private void initializeDefectFormElements() { // try { // foundOn =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("found_on"))));
	 * 
	 * //devResource = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "dev_resource")))); /*defectTitle = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "defect_title")))); sprintId = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("sprint_Id"))));
	 * 
	 * //foundBy = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("found_by")))); defectCategory = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "defect_category")))); description = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("description")))); pbiId =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("pbi_id")))); defectType = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("defect_type")))); //foundOn = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.
	 * getProperty("found_on")))); fixProvidedBy = wait.until(
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
	 * getProperty("owned_by")))); fixProvidedOn = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "fix_provided_on")))); stepsToReproducebtn = wait.until(
	 * ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "steps_to_reproduce")))); stepsToReproduceBody =
	 * wait.until(ExpectedConditions
	 * .visibilityOfElementLocated(By.xpath(locProp.getProperty(
	 * "steps_to_reproduce_body")))); steps =
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
	 * e.getMessage()); } }
	 */
}
