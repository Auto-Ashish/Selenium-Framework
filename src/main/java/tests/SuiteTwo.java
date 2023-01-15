package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductListingPage;
import tests.base.BaseClass;
import utilities.ExcelUtility;
import utilities.JavaScriptExecutorHelperMethods;

public class SuiteTwo extends BaseClass {

	@Test(groups = "Smoke", dataProvider = "ExcelDataProvider")
	public void checkBoxDemo(HashMap<String, String> testData) throws Exception { // Get total count of all the
																					// elements.

		if (driver.getCurrentUrl().contains("myntra"))
			attachScreenshotToReport(true, "/img1.png", "URL verification");
		else
			attachScreenshotToReport(false, "/img1.png", "URL verification");

		HomePage homePageObj = new HomePage(driver); // Created object of HomePage page
		homePageObj.searchForProduct(testData.get("SearchText"));

		ProductListingPage productListingPageObj = new ProductListingPage(driver);
		productListingPageObj.verifyProductListedAndNavigateThroughIt();

		ProductListingPage produListingPageObj = new ProductListingPage(driver);
		productListingPageObj.selectProductWithPricenSize(testData.get("Price"), testData.get("Size")).click();

		Thread.sleep(5000);
		// Window handle code later to be moved in generic method

		String parentWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = (Set<String>) driver.getWindowHandles();
		Iterator<String> it = allWindowHandles.iterator();

		while (it.hasNext()) {
			String childWindow = it.next();
			if (!childWindow.equalsIgnoreCase(parentWindowHandle)) {
				driver.switchTo().window(childWindow);
				System.out.println("Current child window has Window title :" + driver.getTitle());
				attachScreenshotToReport(true, "/img65.png", "Verify Item is selected.  ");
				;
				Thread.sleep(2000);
				driver.close();
			}
		}

		driver.switchTo().window(parentWindowHandle);
		attachScreenshotToReport(true, "/img67.png", "Switched back to Parent window.");

	}

	@Test(enabled = false)
	public void test2() {
		System.out.println("IN suite 2");
	}

}
