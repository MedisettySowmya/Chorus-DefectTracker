package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utilities.WaitUtilsImps;
import base.Browsers;

public class ChorusLoginusinglocatersfile extends Browsers {

    private WaitUtilsImps waitImps;

    @BeforeClass
    public void initUtils() {
        waitImps = new WaitUtilsImps(driver);
    }

    @Test(priority = 1, groups = "login")
    public void UserLogin() {

        //driver.manage().window().maximize();

        String emailAddress = prop.getProperty("Email");
        String password = prop.getProperty("Password");

        waitImps.waitForVisibility(By.xpath(locProp.getProperty("email_field")))
                .sendKeys(emailAddress);

        waitImps.waitForVisibility(By.xpath(locProp.getProperty("next_btn")))
                .click();

        waitImps.waitForVisibility(By.xpath(locProp.getProperty("password_field")))
                .sendKeys(password);

        waitImps.waitForVisibility(By.xpath(locProp.getProperty("sign_in")))
                .click();

        waitImps.waitForVisibility(By.xpath(locProp.getProperty("stay_signed_in")))
                .click();
    }
}
