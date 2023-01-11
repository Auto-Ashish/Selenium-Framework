package tests;
import java.time.Duration;
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
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductListingPage;
import tests.base.BaseClass;

public class SuiteTwo extends BaseClass {
	
		@Test(groups = "Smoke")
		public void checkBoxDemo() throws Exception { // Get total count of all the elements.
			System.out.println("In test method");
			if (driver.getCurrentUrl().contains("myntra"))
				attachScreenshotToReport(true, "/img1.png", "Validate title matched for the web page");
			else
				attachScreenshotToReport(false, "/img1.png", "Validate title matched for the web page");

			HomePage homePageObj = new HomePage(driver); // Created object of HomePage page

			Assert.assertTrue(homePageObj.getLbl_DealOfTheDay().isDisplayed());
			Assert.assertTrue(homePageObj.getTxtbx_Search().isDisplayed());
			System.out.println("Entered 'Computer' keyword in search box.");
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
			System.out.println("Switching all pages. ");
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
				
				Thread.sleep(5000);
				//productListingPageObj.radioMen().click();
				//driver.findElement(By.xpath("//*[text()='Men']/input")).click();
				System.out.println("Select 'Men' category using radio button.");
				WebElement element = driver.findElement(By.xpath("//*[text()='Men']/input/parent::*/*[2]"));
				
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				
				driver.navigate().refresh();
				attachScreenshotToReport(true, "/img61.png", "Verify 'Men' is selected.  " );
				
			
			}
			//Without hard coding xpath by calling xpath through method 
			
			System.out.println("Window Feature Started.");
		 ProductListingPage produListingPageObj = new ProductListingPage(driver);
		 productListingPageObj.selectProductWithPricenSize("Rs. 689", "M").click();
		
		 
		 Thread.sleep(5000);
		 // Window handle code later to be moved in generic method
		 
		 String parentWindowHandle = driver.getWindowHandle();
		 
		 Set<String> allWindowHandles = (Set<String>) driver.getWindowHandles();
		 
		 Iterator<String> it = allWindowHandles.iterator();
		 
		 while (it.hasNext()) {
			
			 String childWindow = it.next();
			 
			 if(!childWindow.equalsIgnoreCase(parentWindowHandle)) {
				 
				 driver.switchTo().window(childWindow);
				 
				 System.out.println("Current child window has Window title :"+driver.getTitle() );
				 attachScreenshotToReport(true, "/img65.png", "Verify Item is selected.  " );;
				 Thread.sleep(2000);
				 driver.close();
			 }
			
		}
		 driver.switchTo().window(parentWindowHandle);
		 attachScreenshotToReport(true, "/img67.png","Switched back to Parent window.");
		 
	}
	
	@Test
	public void test2() {
		System.out.println("IN suite 2");
	}
	
	@Test
	public void test3() {
		System.out.println("IN suite 2");
	}

}
