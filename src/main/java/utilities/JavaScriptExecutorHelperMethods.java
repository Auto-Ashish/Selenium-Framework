package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import tests.base.BaseClass;

//This is common methods helper for executing javascript code in selenium tests
public class JavaScriptExecutorHelperMethods {
	
	// TODO Add loggers and reports support here 
	

	public static void clickUsignJS(WebElement element) {
		
		JavascriptExecutor executor = (JavascriptExecutor)BaseClass.driver;
		executor.executeScript("arguments[0].click();", element);
		
		
	}
	
}
