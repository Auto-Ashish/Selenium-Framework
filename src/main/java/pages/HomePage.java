package pages;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.base.BaseClass;

public class HomePage  implements LocatorsXpathCss {
	
	public HomePage(WebDriver driver) {
	    PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = xpath_txtBoxSearch)
	private WebElement txtbx_Search;
	
	@FindBy(xpath = "//h4[contains(text(),'DEAL OF THE DAY')]")
	private WebElement lbl_DealOfTheDay;
	

	public WebElement getTxtbx_Search() {
		return txtbx_Search;
	}

	public WebElement getLbl_DealOfTheDay() {
		return lbl_DealOfTheDay;
	}	
	
	
	
	
	
	//page specific common methods 
	public void searchForProduct(String productName) throws Exception {
		getTxtbx_Search().sendKeys(productName);
		System.out.println("Entered '"+productName+"' keyword in search box.");

		BaseClass.attachScreenshotToReport(true, "/img2.png", "Validate title matched for the web page");
		getTxtbx_Search().sendKeys(Keys.ENTER);


	}
	
}
