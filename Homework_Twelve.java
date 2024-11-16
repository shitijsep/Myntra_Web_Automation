package com.Homwork;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Twelve extends BaseTest {

	@Test
	public void VerifyMultipleProductsInCart() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("(//ul[@class=\"desktop-navBlock\"]/child::li[2]/a[contains(text(),'T-Shirts')])[1]");
		WebElement tShirts = driver.findElement(by);
		tShirts.click();

		for (int i = 1; i < 4; i++) {
			
			Thread.sleep(2000);

			String productName = String.format("(//div[@class=\"product-productMetaInfo\"]/h3[@class=\"product-brand\"])[%d]", i);
			WebElement Shirt = driver.findElement(by.xpath(productName));
			String brandNameOnHomePage = Shirt.getText();

			String shirtPrices = String.format("(//span[@class=\"product-discountedPrice\"])[%d]", i);
			WebElement tShirtPrice = driver.findElement(by.xpath(shirtPrices));
			String priceOnHomePage = tShirtPrice.getText();
			tShirtPrice.click();

			String parantWindow = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			handles.remove(parantWindow);

			for (String handle : handles) {

				driver.switchTo().window(handle);

				by = By.xpath("//h1[@class=\"pdp-title\"]");
				WebElement brandName = driver.findElement(by);
				String brandNameBeforeAddToBag = brandName.getText();

				if (brandNameBeforeAddToBag.contentEquals(brandNameOnHomePage)) {

					by = By.xpath("//p[@class=\"size-buttons-unified-size\" and text() = \"M\"]");
					WebElement selectSize = driver.findElement(by);
					String selectedSize = selectSize.getText();
					selectSize.click();
					System.out.println(selectedSize);

					Thread.sleep(3000);

//					by = By.xpath("//div[contains(@class,'pdp-add-to-bag')]/span");
					by = By.xpath("//div[@class=\"pdp-description-container\"]/div[2]/div[3]/div/div/span");
					WebElement addToBagBtn = driver.findElement(by);
					addToBagBtn.click();
					System.out.println("Product has successfully added in bag");

					Thread.sleep(10000);
					
					driver.switchTo().window(parantWindow);
					
					Thread.sleep(15000);
				}
			}
			
		}

		by = By.xpath("//a[@class=\"desktop-cart\"]/child::span[1]");
		WebElement bag = driver.findElement(by);
		bag.click();
		System.out.println("Clicking on Bag");

		Thread.sleep(10000);

		by = By.xpath("//div[@class=\"secure\"]");
		WebElement cartPage = driver.findElement(by);
		String cartPageText = cartPage.getText();
		Assert.assertEquals(cartPageText, "100% SECURE");

		by = By.xpath("//div[@class=\"bulkActionStrip-message\"]/span");
		WebElement itenSelectedOnPlaceOrderPage = driver.findElement(by);
		String itenSelectedOnPlaceOrderPageText = itenSelectedOnPlaceOrderPage.getText();
		System.out.println(itenSelectedOnPlaceOrderPageText);

//		3/5 ITEMS SELECTED
		
		int noOfSelectedItem = 0;
		String removeItemselectedTest = itenSelectedOnPlaceOrderPageText.replace(" ITEMS SELECTED", "");
		String[] parts = removeItemselectedTest.split("/");
		for (int i = 0; i < parts.length; i++) {
			String partsOfString = parts[i];
			noOfSelectedItem = Integer.parseInt(partsOfString);
		}

		System.out.println("Total numbers of Item selected "+noOfSelectedItem);

	}

}
