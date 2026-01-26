package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.Browsers;

public class DefectFormElements extends Browsers {

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
		
		/*// Constructor
	    public DefectFormElements() {
	        initializeDefectFormElements(); // Initialize all elements on object creation
	    }*/

	// Method to initialize defect form WebElements
		public void initializeDefectFormElements() {
			try {
				defectTitle = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_title"))));
				sprintId = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("sprint_Id"))));
				devResource = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("dev_resource"))));
				foundBy = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("found_by"))));
				defectCategory = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_category"))));
				description = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("description"))));
				pbiId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("pbi_id"))));
				defectType = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_type"))));
				foundOn = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("found_on"))));
				// fixProvidedBy = wait.until(
				// ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("fix_provided_by"))));
				assignedTo = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("assigned_to"))));
				severity = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("severity"))));
				defectStatus = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_status"))));
				environment = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("environment"))));
				ownedBy = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("owned_by"))));

				// fixProvidedOn = wait.until(
				// ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("fix_provided_on"))));

				stepsToReproducebtn = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("steps_to_reproduce"))));
				//stepsToReproduceBody = wait.until(ExpectedConditions					.visibilityOfElementLocated(By.xpath(locProp.getProperty("steps_to_reproduce_body"))));
				//steps = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("steps"))));
				stepsUploadBtn = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("steps_upload_btn"))));
				expectedResult = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("expected_result"))));
				expectedUploadBtn = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(locProp.getProperty("expected_upload_btn"))));
				actualResult = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("actual_result"))));
				actualUploadBtn = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("actual_upload_btn"))));

				addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("add"))));
				cancelButton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("cancel"))));

			} catch (Exception e) {
				System.out.println("Error initializing defect form elements: " + e.getMessage());
			}
	}
}
