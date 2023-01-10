package tests.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	Properties configProperties = null;
	public static WebDriver driver;

	public ExtentReports report;
	public ExtentTest test;
	String reportPath = new File("").getAbsolutePath().toString().trim() + "/Reports/";

	
	@BeforeTest
	public void createBrowserInstance() {
		configProperties = loadConfiguration();

		startExtentReporting();

		String browserName = configProperties.getProperty("browserType");
		createDriverInstance(browserName);

		driver.get(configProperties.getProperty("driver.baseUrl"));
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));

	}

	@AfterTest
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

	public void startExtentReporting() {
		// ***********Extent Report Implementation***********
		report = new ExtentReports(reportPath + this.getClass().getSimpleName() + ".html", false);

		// Starting the test case for reporting
		test = report.startTest("Extent Report Example Project");

		// ***********Extent Report Implementation***********
	}

	public void closeReporting() {
		// Closing the report
		report.endTest(test);
		report.flush();
		report.close();
	}

	public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		File DestFile = new File(fileWithPath);

		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

	}

	public void attachScreenshotToReport(boolean status, String imgPath, String logMessage) throws Exception {
		takeSnapShot(driver, imgPath);
		if (status) {
			String details = test.addScreenCapture(imgPath);
			test.log(LogStatus.PASS, logMessage, details);
			test.addScreenCapture(imgPath);
		}

		else {
			String details = test.addScreenCapture(imgPath);
			test.log(LogStatus.FAIL, logMessage, details);

		}
	}

}
