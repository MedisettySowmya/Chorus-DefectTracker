package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.Browsers;

public class StatusUpdate extends Browsers {
	String userName;

	/*
	 * @BeforeMethod public void switchToIfframe() { List<WebElement> noiframes =
	 * driver.findElements(By.tagName("iframe"));
	 * System.out.println("Initial Number of iframes: " + noiframes.size());
	 * 
	 * WebElement iframe =
	 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[1]"
	 * ))); driver.switchTo().frame(iframe); }
	 */
	@Test(priority = 3, groups = "projectSelection", dependsOnGroups = "appLunch")
	public void projectSelection() throws InterruptedException {

		new Actions(driver);

		List<WebElement> noiframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Initial Number of iframes: " + noiframes.size());

		WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[1]")));
		driver.switchTo().frame(iframe);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement projectSelectionDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Project']")));
		projectSelectionDropdown.click();
		projectSelectionDropdown.sendKeys("TUVSUD-MEDICI - TUV-SUD");

		WebElement selectProject = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[contains(text(),'TUVSUD-MEDICI - TUV-SUD')]")));
		selectProject.click();

		driver.switchTo().defaultContent();
		// Get logged-in username here itself
		WebElement span = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='ur-nm mr-2']//span[@data-placement='top']")));
		String loggedInUserName = span.getAttribute("title");
		System.out.println("Logged in user name is: " + loggedInUserName);
		userName = loggedInUserName;
		driver.switchTo().frame(iframe);

		// Founnd by Filter

		WebElement foundBy = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='foundBy']")));
		Select selectFoundBy = new Select(foundBy);
		selectFoundBy.selectByVisibleText(userName);

		// Status filter

		/*
		 * WebElement statusFilter = wait
		 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "//select[@formcontrolname='status']"))); // WebElement readyForQAStatus =
		 * wait //
		 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='
		 * Ready // for QA'][1]"))); Select selectStatus = new Select(statusFilter); //
		 * String statusText = readyForQAStatus.getText();
		 * selectStatus.selectByVisibleText("Ready for QA");
		 * 
		 * WebElement noOfActiveDefects = wait.until(
		 * ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * "(//span[@class='page-info']//strong)[last()]"))); int defectCount =
		 * Integer.parseInt(noOfActiveDefects.getText());
		 * System.out.println(defectCount);
		 */
		
		WebElement statusFilter = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@formcontrolname='status']")));
		By countLocator = By.xpath("(//span[@class='page-info']//strong)[last()]");

		String oldCount = wait.until(ExpectedConditions.presenceOfElementLocated(countLocator)).getText();

		Select selectStatus = new Select(statusFilter);
		selectStatus.selectByVisibleText("Ready for QA");

		// Optional: wait a bit for the grid to refresh or loader to disappear
		Thread.sleep(1000); // replace with explicit wait for loader if available

		// Get elements
		List<WebElement> counts = driver.findElements(countLocator);

		int readyForQAdefectCount = 0;
		if (!counts.isEmpty()) {
			String countText = counts.get(0).getText().replaceAll("\\D", ""); // remove non-digits
			if (!countText.isEmpty()) {
				readyForQAdefectCount = Integer.parseInt(countText);
			}
		}

		System.out.println("Filtered defect count: " + readyForQAdefectCount);
		
		if(readyForQAdefectCount == 0)
		{
			selectStatus.selectByVisibleText("QA In progress");
			
			Thread.sleep(1000);
			String oldCountofQAInProgress = wait.until(ExpectedConditions.presenceOfElementLocated(countLocator)).getText();

			// Get elements
			List<WebElement> QAcounts = driver.findElements(countLocator);

			int qaInProgressForQAdefectCount = 0;
			if (!QAcounts.isEmpty()) {
				String countText = QAcounts.get(0).getText().replaceAll("\\D", ""); // remove non-digits
				if (!countText.isEmpty()) {
					qaInProgressForQAdefectCount = Integer.parseInt(countText);
				}
			}
			
		}

		if (readyForQAdefectCount != 0) {
			for (int i = 0; i <= readyForQAdefectCount; i++) {

				// Click on Edit icon
				WebElement editIcon = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@title='Edit Defect'][1]")));
				editIcon.click();

				WebElement defectStatus = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//select[@formcontrolname='defectStatus']")));

				WebElement qaInProgressStatus = wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//select[@formcontrolname='defectStatus']//option[@value='6']")));
				Select selectDefectStatus = new Select(defectStatus);
				String qaInProgressStatusText = qaInProgressStatus.getText();
				selectDefectStatus.selectByVisibleText(qaInProgressStatusText);

				// Click on Update Button
				WebElement updateBtn = wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Update')]")));

				if (updateBtn.isEnabled()) {
					updateBtn.click();
				}
			}
		}
	}

	// @Test(priority = 4, groups = "qaDefectStatusIpdate",
	// dependsOnGroups="projectSelection")
	public void QADefectStatuUpdate() {

	}

	public void DEVDefectStatuUpdate() {

	}

}
