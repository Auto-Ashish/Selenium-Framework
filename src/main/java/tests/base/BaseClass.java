package tests.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtility;

public class BaseClass {

	Properties configProperties = null;
	public static WebDriver driver;

	public static ExtentReports report;
	public static ExtentTest test;
	static String reportPath = new File("").getAbsolutePath().toString().trim() + "/Reports/";

	@BeforeMethod
	//@BeforeClass
	public void createBrowserInstance() {
		configProperties = loadConfiguration();

		startExtentReporting(this.getClass().getSimpleName());

		String browserName = configProperties.getProperty("browserType");
		createDriverInstance(browserName);

		driver.get(configProperties.getProperty("driver.baseUrl"));
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));

	}
	

	@AfterMethod
	public void closeBrowserInstance() {
		// driver.close();
		closeReporting();
		driver.quit();
	}

	public void createDriverInstance(String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("Invalid browserName provided in properties file");
			break;
		}

	}

	public Properties loadConfiguration() {
		String configurationFileName = System.getProperty("executionProfile", "") + ".properties";
		Properties prop = null;
		try {
			prop = readPropertiesFile(System.getProperty("user.dir") + "\\src\\main\\resources\\configuration\\" + configurationFileName);
		} catch (IOException e) {
			System.out.println("Error while reading file ");
			e.printStackTrace();
		}
		System.out.println("baseUrl: " + prop.getProperty("driver.baseUrl"));
		System.out.println("browserType: " + prop.getProperty("browserType"));

		return prop;

	}

	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

	public static void startExtentReporting(String name) {
		// ***********Extent Report Implementation***********
		report = new ExtentReports(reportPath + name + ".html", false);

		// Starting the test case for reporting
		test = report.startTest("Extent Report Example Project");

		// ***********Extent Report Implementation***********
	}

	public static void closeReporting() {
		// Closing the report
		report.endTest(test);
		report.flush();
		report.close();
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		File DestFile = new File(fileWithPath);

		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

	}

	public static void attachScreenshotToReport(boolean status, String imgPath, String logMessage) throws Exception {
		takeSnapShot(driver, imgPath);
		if (status) {
			String details = test.addScreenCapture(imgPath);
			test.log(LogStatus.PASS, "Verification : PASSED " +logMessage, details);
			test.addScreenCapture(imgPath);
		}

		else {
			String details = test.addScreenCapture(imgPath);
			test.log(LogStatus.FAIL, "Verification : FAILED " +logMessage, details);

		}
	}
	
	
	@DataProvider(name = "ExcelDataProvider")
	public Object[] dpMethod() throws IOException {
		List<Object> list=ExcelUtility.createTestDataFromExecel("E:\\AutoMation\\eclipse-workspace\\0.1\\TestData\\TestData.xlsx","ABC");
		return   list.toArray() ;

	}

}
