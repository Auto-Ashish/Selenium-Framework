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
	
	@FindBy(xpath = "//*[text()='Men']/input")
	private WebElement radioMen;
	
	public WebElement radioMen() {
		return radioMen;
	}
	
	
//	@FindBy(xpath = "//input[@value='Frames']")
	
	
	
	private WebElement checkFrames;
	
	public WebElement checkFrames() {
		return checkFrames;
	}
	
	
	
	public WebElement selectProductWithPricenSize(String price, String size) {
		String xpath= String.format("(//span[contains(.,'%s')]/ancestor::div[@class='product-price']/preceding-sibling::h4[@class='product-sizes']/span[text()='%s']//ancestor::li[@class='product-base'])[1]", price,size);
	
		return BaseClass.driver.findElement(By.xpath(xpath));
	
	}
	
	
}
