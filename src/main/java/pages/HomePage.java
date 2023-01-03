package pages;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public HomePage(WebDriver driver) {
	    PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//input[@class='desktop-searchBar']")
	private WebElement txtbx_Search;
	
	@FindBy(xpath = "//h4[contains(text(),'DEAL OF THE DAY')]")
	private WebElement lbl_DealOfTheDay;
	

	public WebElement getTxtbx_Search() {
		return txtbx_Search;
	}

	public WebElement getLbl_DealOfTheDay() {
		return lbl_DealOfTheDay;
	}	
	
	
	
}
