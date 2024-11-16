package com.Homwork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Homework_Ten extends BaseTest {

	/**
	 * 1. Go to "MEN" section and search for "T-shirt". 2. Apply "Price" filter
	 * (e.g., ₹500-₹1000).
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void VerifyFilterTshirtsByPrice() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("(//ul[@class=\"desktop-navBlock\"]/child::li[2]/a[contains(text(),'T-Shirts')])[1]");
		WebElement tShirts = driver.findElement(by);
		tShirts.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");

		Thread.sleep(3000);

		by = By.xpath("//div[@class=\"verticalFilters-filters\"]/span[@class=\"verticalFilters-header\"]");
		WebElement priceHeader = driver.findElement(by);
		String priceHeaderText = priceHeader.getText();
		Assert.assertEquals(priceHeaderText, "PRICE");

		Thread.sleep(3000);

		by = By.xpath("//div[@class=\"slider-rootRailThumbRight\"]");
		WebElement sliderRootRailThumbRight = driver.findElement(by);
		int xOffset = -152;
		act.clickAndHold(sliderRootRailThumbRight).moveByOffset(xOffset, 0).release().perform();

		Thread.sleep(3000);

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");

		by = By.xpath("//div[@class=\"slider-dotContainer\"]");
		WebElement sliderDotContainerPrice = driver.findElement(by);
		String sliderDotContainerPriceInText = sliderDotContainerPrice.getText();
		System.out.println("This Selected Price reange " + sliderDotContainerPriceInText);
//		₹100 - ₹2,500
		int selectedMin = 0, selectedmax = 0;
		String replaceRs = sliderDotContainerPriceInText.replace("₹", "");
		String replaceRsComa = replaceRs.replace(",", "");
		String[] partsq = replaceRsComa.split(" - ");
		for (String string : partsq) {
			selectedMin = Integer.parseInt(string);
			System.out.println(selectedMin);

			if (selectedmax < selectedMin) {
				int temp = 0;
				temp = selectedMin;
				selectedMin = selectedmax;
				selectedmax = temp;
			}
		}
		System.out.println("My selectedmin Value is " + selectedMin);
		System.out.println("My selectedmax Value is " + selectedmax);

//		Expected value...Rs. 100 To Rs. 2500
		by = By.xpath("//div[@class=\"filter-summary-filter\"]");
		WebElement filterSummaryPrice = driver.findElement(by);
		String filterSummaryPriceInText = filterSummaryPrice.getText();
		System.out.println("This Expected Price reange " + filterSummaryPriceInText);
		int expectedMin = 0, expectedMax = 0;
		String filterSummaryPriceInTextWithoutRs = filterSummaryPriceInText.replace("Rs. ", "");
		String[] partsOfFilterSummaryPriceInTextWithoutRs = filterSummaryPriceInTextWithoutRs.split(" ");

		for (int j = 0; j < partsOfFilterSummaryPriceInTextWithoutRs.length; j++) {
			if (j % 2 == 0) {
				String t = partsOfFilterSummaryPriceInTextWithoutRs[j];
				expectedMin = Integer.parseInt(t);
				System.out.println(expectedMin);
			}

			if (expectedMax < expectedMin) {
				int temp = 0;
				temp = expectedMin;
				expectedMin = expectedMax;
				expectedMax = temp;
			}
		}
		System.out.println("My expectedMin Value is " + expectedMin);
		System.out.println("My expectedMax Value is " + expectedMax);

		Assert.assertEquals(selectedMin, expectedMin);
		Assert.assertEquals(selectedmax, expectedMax);

		by = By.xpath("//div[@class=\"product-price\"]/span/span[@class=\"product-discountedPrice\"]");
		List<WebElement> productDiscountedPrice = driver.findElements(by);

		int defaultCountOfProductDiscountedPrice = 0, CountOfProductDiscountedPrice = 0;
		for (int i = 0; i < productDiscountedPrice.size(); i++) {
			String productDiscountedPriceText = productDiscountedPrice.get(i).getText();
			System.out.println(productDiscountedPriceText);
//			Rs. 854
			String productDiscountedPriceTextInNumric = productDiscountedPriceText.replace("Rs. ", "");

			int productDiscountedPriceInInteger = Integer.parseInt(productDiscountedPriceTextInNumric);
//			System.out.println(productDiscountedPriceInInteger);

			if (expectedMin <= productDiscountedPriceInInteger & productDiscountedPriceInInteger <= expectedMax) {
				CountOfProductDiscountedPrice++;
			} else {
				defaultCountOfProductDiscountedPrice++;
			}

			SoftAssert softly = new SoftAssert();

			softly.assertTrue(defaultCountOfProductDiscountedPrice == 0, "PASS");

			softly.assertAll();

		}

	}
}
