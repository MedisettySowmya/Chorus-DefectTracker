package interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WaitUtils {

	WebElement waitForVisibility(By locator);

}
