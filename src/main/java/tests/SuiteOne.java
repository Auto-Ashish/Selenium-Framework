package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.ProductListingPage;
import tests.base.BaseClass;

public class SuiteOne extends BaseClass {

	@Test(groups = "Smoke", enabled = true)
	public void test1() throws Exception {
		System.out.println("In test method");
		if (driver.getCurrentUrl().contains("myntra"))
			attachScreenshotToReport(true, "/img1.png", "Validate title matched for the web page");
		else
			attachScreenshotToReport(false, "/img1.png", "Validate title matched for the web page");

		HomePage homePageObj = new HomePage(driver);

		Assert.assertTrue(homePageObj.getLbl_DealOfTheDay().isDisplayed());
		Assert.assertTrue(homePageObj.getTxtbx_Search().isDisplayed());

		homePageObj.getTxtbx_Search().sendKeys("Mobile");
		attachScreenshotToReport(true, "/img2.png", "Validate title matched for the web page");
		homePageObj.getTxtbx_Search().sendKeys(Keys.ENTER);

	}

	@Test(groups = "Smoke", enabled = true)
	public void verifyProductCountFromProductListingPage() throws Exception {
		System.out.println("In test method");
		if (driver.getCurrentUrl().contains("myntra"))
			attachScreenshotToReport(true, "/img1.png", "Validate title matched for the web page");
		else
			attachScreenshotToReport(false, "/img1.png", "Validate title matched for the web page");

		HomePage homePageObj = new HomePage(driver); // Created object of HomePage page

		Assert.assertTrue(homePageObj.getLbl_DealOfTheDay().isDisplayed());
		Assert.assertTrue(homePageObj.getTxtbx_Search().isDisplayed());

		homePageObj.getTxtbx_Search().sendKeys("Mobile");
		attachScreenshotToReport(true, "/img2.png", "Validate title matched for the web page");
		homePageObj.getTxtbx_Search().sendKeys(Keys.ENTER);

		ProductListingPage productListingPageObj = new ProductListingPage(driver); // Created object of
																					// ProductListingPage page to access
																					// elements of ProductListing page

		Assert.assertEquals(productListingPageObj.getProducts().size() > 0, true);
		attachScreenshotToReport(true, "/img3.png",
				"Validate size is greater than zero, Size is:  " + productListingPageObj.getProducts().size());

	}

	@Test(groups = "Smoke")
	public void countAllElementsFromProductListingPage() throws Exception { // Get total count of all the elements.
		System.out.println("In test method");
		if (driver.getCurrentUrl().contains("myntra"))
			attachScreenshotToReport(true, "/img1.png", "Validate title matched for the web page");
		else
			attachScreenshotToReport(false, "/img1.png", "Validate title matched for the web page");

		HomePage homePageObj = new HomePage(driver); // Created object of HomePage page

		Assert.assertTrue(homePageObj.getLbl_DealOfTheDay().isDisplayed());
		Assert.assertTrue(homePageObj.getTxtbx_Search().isDisplayed());

		homePageObj.getTxtbx_Search().sendKeys("Computer");
		attachScreenshotToReport(true, "/img2.png", "Validate title matched for the web page");
		homePageObj.getTxtbx_Search().sendKeys(Keys.ENTER);

		ProductListingPage productListingPageObj = new ProductListingPage(driver); // Created object of
																					// ProductListingPage page to access
																					// elements of ProductListing page

		Assert.assertEquals(productListingPageObj.getProducts().size() > 0, true);
		attachScreenshotToReport(true, "/img3.png",
				"Validate size is greater than zero, Size is:  " + productListingPageObj.getProducts().size());

		
		//
		int count = productListingPageObj.getProducts().size();
		try {
			
			while(productListingPageObj.getNextbtn().isDisplayed()) {
				if(productListingPageObj.getNextbtn().isDisplayed()){
					productListingPageObj.getNextbtn().click();
					Thread.sleep(5000);
					count = count + productListingPageObj.getProducts().size();
				}
				else
					break;
			}
			attachScreenshotToReport(true, "/img4.png", "Total Elements in inventory :  " + count);
			
		}	catch (NoSuchElementException e) {
			System.out.println("On last page");
			attachScreenshotToReport(true, "/img5.png", "Total Elements in inventory :  " + count);
		}
	
		
		

	}

}
