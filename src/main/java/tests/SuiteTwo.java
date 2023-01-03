package tests;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import tests.base.BaseClass;

public class SuiteTwo extends BaseClass {
	
	@Test
	public void test1() {
	System.out.println("IN suite 1111111111111111111111111");
	/*
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(
	 * "8149547129"); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS);
	 * driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).
	 * click();
	 */
	
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
