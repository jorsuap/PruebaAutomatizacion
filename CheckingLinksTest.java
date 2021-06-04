package com.checkinglinks.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckingLinksTest {

	WebDriver driver;
	CheckingLinksPage page;
		
	@Before
	public void beforClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		page = new CheckingLinksPage(driver);
		driver.get("https://www.choucairtesting.com/empleos-testing/");
	}
	
	@Test
	public void  f() {
		assertTrue(page.checkingPageLinks(), "There are brokenLinks");
		
	}
	@After
	public void afterClass() {
		driver.close();
	}
}
