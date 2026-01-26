/*//package testcases;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Properties;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;
//
//import base.Browsers;
//
//public class ChorusLogin extends Browsers {
//
//	@Test(priority = 1)
//	public static void UserLogin() {
//		String expectedTitle = "Chorus"; // Title before redirection
//
//		// Declare all WebElement variables at the top
//		WebElement emailField = null, nextButton = null, passwordField = null, signInButton = null,
//				staySignedInNobtn = null;
//
//		// Create a WebDriverWait object for explicit waits, declared only once
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		// maximize the window
//		driver.manage().window().maximize();
//
//		// Wait for the initial page to load and get its title
//		String actualTitle = driver.getTitle();
//		if (expectedTitle.equals(actualTitle)) {
//			System.out.println("Chorus page loaded, proceeding with the login.");
//
//			// Retrieve the email address and password from the properties file
//			String emailAddress = prop.getProperty("Email");
//			String password = prop.getProperty("Password");
//
//			try {
//				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//				// Wait for the page to be redirected and ensure we're on the Microsoft login
//				System.out.println("Redirected to Microsoft login page.");
//
//				// Locate and interact with the Chorus page elements (before redirection)
//				emailField = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='i0116']")));
//				nextButton = driver.findElement(By.xpath("//input[@id='idSIButton9']"));
//
//				// Enter email and proceed to the next page (which will redirect)
//				System.out.println("Entering email address...");
//				emailField.sendKeys(emailAddress);
//				nextButton.click();
//				passwordField = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='i0118']")));
//				signInButton = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='idSIButton9']")));
//
//				// Enter password and submit
//				System.out.println("Entering password...");
//				passwordField.sendKeys(password);
//				signInButton.click();
//				staySignedInNobtn = wait
//						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='idBtn_Back']")));
//
//				staySignedInNobtn.click();
//
//			} catch (Exception e) {
//				System.out.println("Error during login: " + e.getMessage());
//			}
//		} else {
//			System.out.println("Chorus page not loaded correctly. Current title: " + actualTitle);
//		}
//	}
//
//	@Test(priority = 2)
//	public void SignOut() {
//		// Ensure wait is properly initialized
//		if (wait == null) {
//			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		}
//
//		// Now you can use the wait object safely
//		WebElement userName = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Chorus Automation')]")));
//		System.out.println("Chorus user name: " + userName.getText());
//
//		// Check if the user name matches "Chorus Automation"
//		if (userName.getText().equals("Chorus Automation")) {
//			// Locate the sign-out button and click it
//			WebElement signOutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//					"//div[@class='hd-rt']//div[@class='lg-ic']//a[@href='#']//i[contains(@class,'fa fa-sign-out fa-lg')]")));
//			signOutBtn.click();
//			System.out.println("Successfully Signed Out");
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			System.out.println("User is not 'Chorus Automation', unable to sign out.");
//		}
//	}
//}
*/