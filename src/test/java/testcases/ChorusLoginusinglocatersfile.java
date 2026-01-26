package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.Browsers;

public class ChorusLoginusinglocatersfile extends Browsers {

	@Test(priority = 1, groups="login")
	public static void UserLogin() throws InterruptedException {
		
		driver.get(prop.getProperty("ChorusURL"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();
		String emailAddress = prop.getProperty("Email");
		String password = prop.getProperty("Password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("email_field"))))
				.sendKeys(emailAddress);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("next_btn")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("password_field"))))
				.sendKeys(password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("sign_in")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("stay_signed_in"))))
				.click();
		Thread.sleep(1000);
	}

	// @DataProvider(name = "testData")
	// public Object[][] Creadentials() {
	// return new Object[][] {
	// { "sowmya.medisetty@cognine.com", "Bharathi@369" },
	// { "chorus.Automation@cognine.com", "Welcome2Cognine" },
	// };
	// }

}
