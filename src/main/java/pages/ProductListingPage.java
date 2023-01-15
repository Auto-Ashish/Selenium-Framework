package pages;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import tests.base.BaseClass;
import utilities.JavaScriptExecutorHelperMethods;

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


	public void verifyProductListedAndNavigateThroughIt() throws Exception {
		Assert.assertEquals(getProducts().size() > 0, true);
		
		BaseClass.attachScreenshotToReport(true, "/img3.png", "Validate size is greater than zero, Size is:  " + getProducts().size());		
		System.out.println("Navigation through prodult list");
		
		int count = getProducts().size();
		
		try {
			
			while(getNextbtn().isDisplayed()) {
				if(getNextbtn().isDisplayed()){
					getNextbtn().click();
					Thread.sleep(5000);
					count = count + getProducts().size();
				}
				else
					break;
			}
			
			BaseClass.attachScreenshotToReport(true, "/img4.png", "Total Elements in inventory :  " + count);
			
		}	catch (NoSuchElementException e) {
			System.out.println("On last page");
			BaseClass.attachScreenshotToReport(true, "/img5.png", "Total Elements in inventory :  " + count);	
			Thread.sleep(5000);	
			System.out.println("Select 'Men' category using radio button.");
		
			try {
				WebElement element = BaseClass.driver.findElement(By.xpath("//*[text()='Men']/input/parent::*/*[2]"));
				
				JavaScriptExecutorHelperMethods.clickUsignJS(element);
			} catch (NoSuchElementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();  // Ignore 
			}
			
			BaseClass.driver.navigate().refresh();
			
			BaseClass.attachScreenshotToReport(true, "/img61.png", "Verify 'Men' is selected.  " );
			
		
		}
		
	}
	
	
}
