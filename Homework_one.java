package com.Homwork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/*
 * Search Functionality
 * Scenario: Verify that the search bar works properly on the dashboard.
 */


public class Homework_one extends BaseTest{

	@Test
	public void VerifyThatTheSearchbarWorksProperlyOnTheDashboard() {

		By by = By.xpath("//input[@class=\"desktop-searchBar\"]");
		WebElement searchField = driver.findElement(by);
		searchField.click();
		searchField.sendKeys("Shirt");

		by = By.xpath("//a[@class=\"desktop-submit\"]");
		WebElement searchbar = driver.findElement(by);
		searchbar.click();

		by = By.xpath("//h4[@class=\"product-product\"]");
		List<WebElement> shirtList = driver.findElements(by);

		SoftAssert softly = new SoftAssert();

		for (int i = 0; i < shirtList.size(); i++) {

			String shirt = shirtList.get(i).getText();
			System.out.println(shirt);
			softly.assertTrue(shirt.endsWith(shirt));

		}

		softly.assertAll();
	}
}
