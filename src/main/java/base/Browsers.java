package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browsers {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop = new Properties();
	public static Properties locProp = new Properties();
	public static FileReader configFileReader;
	public static FileReader locatorFileReader;

	@BeforeClass
	public void setup() throws IOException {
		if (driver == null) { 

			// Load the properties files
			configFileReader = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
			locatorFileReader = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config\\locators.properties");

			locProp.load(locatorFileReader);
			prop.load(configFileReader);

			String browserName = prop.getProperty("browser");
			if (browserName == null || browserName.isEmpty()) {
				throw new RuntimeException("Browser name not specified in the config file.");
			}

			String chorusURL = prop.getProperty("ChorusURL");
			if (chorusURL == null || chorusURL.isEmpty()) {
				throw new RuntimeException("ChorusURL not specified in the config file.");
			}

			// Initialize the WebDriver based on the browser selected in config
			switch (browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--inprivate");
				driver = new EdgeDriver(options);
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			default:
				throw new IllegalArgumentException("Invalid browser name: " + browserName);
			}
			driver.manage().window().maximize();

			// Initialize WebDriverWait after WebDriver initialization
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
	}

	@AfterSuite
	public void BrowserClose() {
		// Optional: You can close the browser here if you want after each test method
		// If you want to keep the browser open after all tests, move the driver.quit()
		// @AfterClass
		if (driver != null) {
			driver.quit();
			System.out.println("Browser closed.");
		}
	}
}
