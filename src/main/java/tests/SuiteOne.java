package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import tests.base.BaseClass;

public class SuiteOne extends BaseClass {

	@Test(groups = "Smoke")
	public void test1() throws Exception {
		System.out.println("In test method");
		if (driver.getCurrentUrl().contains("myntra"))
			attachScreenshotToReport(true, "/img1.png", "Validate title matched for the web page");
		else
			attachScreenshotToReport(false, "/img1.png", "Validate title matched for the web page");
		
		HomePage homePageObj=new HomePage(driver);
		
		Assert.assertTrue(homePageObj.getLbl_DealOfTheDay().isDisplayed());
		Assert.assertTrue(homePageObj.getTxtbx_Search().isDisplayed());
		
		homePageObj.getTxtbx_Search().sendKeys("Mobile");
		attachScreenshotToReport(true, "/img2.png", "Validate title matched for the web page");
		homePageObj.getTxtbx_Search().sendKeys(Keys.ENTER);
		
		
		
		

	}

}
