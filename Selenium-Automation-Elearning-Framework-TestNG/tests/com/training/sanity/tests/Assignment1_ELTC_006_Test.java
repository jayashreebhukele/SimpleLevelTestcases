package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.Assignment1_ELTC_006_POM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class Assignment1_ELTC_006_Test {

	private WebDriver driver;
	private String baseUrl;
	private Assignment1_ELTC_006_POM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
        driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new Assignment1_ELTC_006_POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
		driver.quit();
	}
	@Test
	public void validLoginTest() throws InterruptedException {
		loginPOM.sendUserName("jayashree2");
		loginPOM.sendPassword("jayashree@123");
		Thread.sleep(2000);
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("First");
		loginPOM.assertpagetitle();
		
	}

}
