package com.Homwork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Seven extends BaseTest {

	/*
	 * Scenario : Ensure that the offers and discounts are displayed on the
	 * dashboard.
	 * 
	 * 1.Open the Myntra website. 2.Navigate to the homepage/dashboard. 3.Locate the
	 * "Offers and Discounts" section. 4.Verify that after selecting Discount Range
	 * discounted product are visible or not with discounted price.
	 * 
	 */

	@Test
	public void verifyOffersAndDiscountsSection() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("//a[text() = \"Kurtas & Kurta Sets\"]");
		WebElement kurtasKurtaSets = driver.findElement(by);
		kurtasKurtaSets.click();

		Thread.sleep(3000);

		by = By.xpath("//span[text() = \"Discount Range\"]");
		WebElement disCountRangeTag = driver.findElement(by);
		String disCountRangeTagName = disCountRangeTag.getText();
		Assert.assertEquals(disCountRangeTagName, "DISCOUNT RANGE");

		Thread.sleep(3000);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");

		Thread.sleep(4000);

		by = By.xpath("//ul[@class=\"discount-list\"]/li[8]/label");
		WebElement discountInPercentage = driver.findElement(by);
		discountInPercentage.click();

		Thread.sleep(3000);

		by = By.xpath("//div[@class=\"filter-summary-filter\"]/span");
		WebElement tagAfterdisCountRangeApply = driver.findElement(by);
		String tagAfterdisCountRangeApplyTest = tagAfterdisCountRangeApply.getText();
		Assert.assertEquals(tagAfterdisCountRangeApplyTest, "80 % And Above");

		Thread.sleep(3000);

		by = By.xpath("//div[@class=\"product-price\"]/span[2]");
		List<WebElement> discountInPercentageAfterFilter = driver.findElements(by);

		for (int i = 0; i < discountInPercentageAfterFilter.size(); i++) {

			String discountInPercentageAfterFilterText = discountInPercentageAfterFilter.get(i).getText();
			System.out.println(discountInPercentageAfterFilterText);

			String discountInPercentageAfterFilterTextString = discountInPercentageAfterFilterText.substring(1, 3);
			System.out.println(discountInPercentageAfterFilterTextString);

			int price = Integer.parseInt(discountInPercentageAfterFilterTextString);

			Assert.assertTrue(price >= 80 && price <= 100, discountInPercentageAfterFilterTextString);
						
			
			
			
		}

	}

}
