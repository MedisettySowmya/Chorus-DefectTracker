package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.WaitUtils;

public class WaitUtilsImps implements WaitUtils{

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtilsImps(WebDriver driver) {   // same as class name
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
}
