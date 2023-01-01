package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import tests.base.BaseClass;

public class SuiteOne extends BaseClass {

	@Test(groups = "Smoke")
	public void test1() throws Exception {
		System.out.println("In test method");
		if (driver.getCurrentUrl().contains("myntra"))
			attachScreenshotToReport(true, "Reports/img1.jpeg", "Validate title matched for the web page");
		else
			attachScreenshotToReport(true, "Reports/img1.jpeg", "Validate title matched for the web page");

	}

}
