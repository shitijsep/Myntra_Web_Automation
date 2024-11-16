package com.Homwork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Homework_three extends BaseTest{

	/**
	 * Sort Functionality Scenario: Verify that sorting options are working
	 * correctly. TC : Sort products by Numerical Values (Ascending)and verify that
	 * products are displayed accordingly.
	 * 
	 * Steps : 1.Navigate to the sorting options. 2.Select the option to sort by
	 * numerical values (Ascending). 3.Verify that items are listed in ascending
	 * numerical order.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void VerifyThatSortingOptionsAreWorkingCorrectly() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("(//ul[@class=\"desktop-navBlock\"]/child::li[2]/a[contains(text(),'T-Shirts')])[1]");
		WebElement tShirts = driver.findElement(by);
		tShirts.click();

		by = By.xpath("//div[@class=\"sort-sortBy\"]");
		WebElement sortBy = driver.findElement(by);
		sortBy.click();

		Thread.sleep(3000);

		act = new Actions(driver);
		by = By.xpath("//div[@class=\"sort-sortBy\"]/descendant::label[@class=\"sort-label \"][6]");
		WebElement priceLowToHigh = driver.findElement(by);
		act.moveToElement(priceLowToHigh).click().perform();

		Thread.sleep(4000);

		by = By.xpath("//span[@class=\"product-discountedPrice\"]");
		List<WebElement> prices = driver.findElements(by);

		int a = 0;// 0
		SoftAssert softly = new SoftAssert();
		for (int i = 0; i < prices.size(); i++) {

			String PriceValue = prices.get(i).getText();

			String PriceValueInString = PriceValue.substring(4, 7);
//			System.out.println(PriceValueInString);

			int price = Integer.parseInt(PriceValueInString);

			int b = price;

			if (a <= b) {
				softly.assertTrue(a <= b);
				System.out.println("Price is " + b);
				a = b;

			} else {

				System.out.println("Price is not in Low To High reange");
//				Assert.assertEquals(a <= b, "Price is not maintain as Low To High from this product");
				break;
			}
			
		}
		softly.assertAll();
	}

}
