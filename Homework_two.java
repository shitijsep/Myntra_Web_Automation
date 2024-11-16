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

public class Homework_two extends BaseTest {

	/*
	 * Product Filtering Scenario: Verify that the filter option works on the
	 * product listing page.
	 * 
	 */

	@Test
	public void VerifyThatTheFilterOptionWorksOnTheProductListingPage() {

		By by = By.xpath("//input[@class=\"desktop-searchBar\"]");
		WebElement searchField = driver.findElement(by);
		searchField.click();
		searchField.sendKeys("ADIDAS");

		by = By.xpath("//a[@class=\"desktop-submit\"]");
		WebElement searchbar = driver.findElement(by);
		searchbar.click();

		by = By.xpath("//ul[@class=\"gender-list\"]/child::li[1]");
		WebElement menCheckBox = driver.findElement(by);
		menCheckBox.click();

		by = By.xpath("//h4[@class=\"product-product\"]");
		List<WebElement> menList = driver.findElements(by);

		by = By.xpath("//h3[@class=\"product-brand\"]");
		List<WebElement> brandNameList = driver.findElements(by);

		SoftAssert softly = new SoftAssert();

		for (int i = 0; i < menList.size(); i++) {

			String brandnamelist = brandNameList.get(i).getText();
			System.out.println(brandnamelist);
			softly.assertTrue(brandnamelist.startsWith("ADIDAS"));

		}

		softly.assertAll();
	}

}
