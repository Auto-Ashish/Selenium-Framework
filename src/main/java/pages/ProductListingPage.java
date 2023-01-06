package pages;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.base.BaseClass;

public class ProductListingPage {
	
	public ProductListingPage(WebDriver driver) {
	    PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//ul/li[@class='product-base']")
	private List<WebElement> products;
	
	public List<WebElement> getProducts() {
		return products;
	}

	
	@FindBy(xpath = "//a[text()='Next']") //Next button WebElement
	private WebElement Nextbtn;
	
	public WebElement getNextbtn() {
		return Nextbtn;
	}
	
	
	public WebElement getButton(String buttonText) {
		return BaseClass.driver.findElement(By.xpath("//a[text()='"+buttonText+"']"));
	}
	
	
	
}
