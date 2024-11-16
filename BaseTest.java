package com.Homwork;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	RemoteWebDriver driver;
	
	@BeforeMethod
	public void setup() {

		ChromeOptions option = new ChromeOptions();

		HashMap<String, Object> pref = new HashMap<String, Object>();
		pref.put("profile.default_content_setting_values.geolocation", 2); // 2 for block

		option.setExperimentalOption("prefs", pref);
		option.addArguments("--incognito");
		option.addArguments("--start-maximized");
		option.addArguments("--disable-notification"); // to block all notofication

		driver = new ChromeDriver(option);
		driver.get("https://www.myntra.com");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
	
	
}
