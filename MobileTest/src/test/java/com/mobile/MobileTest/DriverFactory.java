package com.mobile.MobileTest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class DriverFactory 
{
	public static AppiumDriver<WebElement> driver;
	public static DesiredCapabilities cap;
	
	public static void Android_LaunchApp() throws MalformedURLException {
		
		DesiredCapabilities cap = new DesiredCapabilities();

		// Platform version

	
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "My New Phone");
		cap.setCapability("UdiD", "R58M481MXVF");
		
		cap.setCapability("appPackage", "com.google.android.gm");
		cap.setCapability("appActivity", "com.google.android.gm.ui.MailActivityGmail");
	
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		Assert.assertNotNull(driver);
		MobileDriver.setWebDriver(driver);
	}
	
	
	
	public static void CloseApp() {
		driver.quit();
	}
}
