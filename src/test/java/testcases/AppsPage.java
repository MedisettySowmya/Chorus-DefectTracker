package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utilities.WaitUtilsImps;
import base.Browsers;

public class AppsPage extends Browsers{
	
	WaitUtilsImps waitImps;
	@BeforeClass
	public void setup()
	{
		waitImps = new WaitUtilsImps(driver);
	}
	
	@Test(priority = 2, groups = "appLunch", dependsOnGroups = "login") 
	public void AppLunch() {
        System.out.println("Launching defect tracker app");
        waitImps.waitForVisibility(By.xpath(locProp.getProperty("defect_tracker_app"))).click();
        System.out.println("Successfully launched the defect tracker app");


		// Wait for the defect tracker link to be clickable and click on it
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locProp.getProperty("defect_tracker_app"))))
				//.click();
		System.out.println("Successfully launched the defect tracker app");
	}

}
