package com.Homwork;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Eleven extends BaseTest {

	/**
	 * Steps: 1. Select any product in Women’s section. 2. Check available sizes.
	 * 
	 * Expected Result is : Available sizes are displayed, and unavailable sizes are
	 * disabled or marked.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void VerifySizeAvailabilityForAProduct() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("(//ul[@class=\"desktop-navBlock\"]/child::li[2]/a[contains(text(),'T-Shirts')])[1]");
		WebElement tShirts = driver.findElement(by);
		tShirts.click();

		by = By.xpath("(//h3[@class=\"product-brand\"])[2]");
		WebElement secondtShirt = driver.findElement(by);
		String brandNameOnHomePage = secondtShirt.getText();

		by = By.xpath("(//span[@class=\"product-discountedPrice\"])[2]");
		WebElement scndTShirtPrice = driver.findElement(by);
		String priceOnHomePage = scndTShirtPrice.getText();
		System.out.println(priceOnHomePage);// Rs. 575
		String priceOnHomePageRemoveRs = priceOnHomePage.replace("Rs. ", "");
		int priceOnHomePageInInt = Integer.parseInt(priceOnHomePageRemoveRs);
		scndTShirtPrice.click();

		String parantWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		handles.remove(parantWindow);
		for (String handle : handles) {

			driver.switchTo().window(handle);

			by = By.xpath("//h1[@class=\"pdp-title\"]");
			WebElement brandName = driver.findElement(by);
			String brandNameBeforeAddToBag = brandName.getText();

			by = By.xpath("//p[@class=\"pdp-discount-container\"]/span[@class=\"pdp-price\"]");
			WebElement brandPrice = driver.findElement(by);
			String brandPriceBeforeAddToBag = brandPrice.getText();
			System.out.println(brandPriceBeforeAddToBag);// ?575
			String brandPriceBeforeAddToBagRemoveRs = brandPriceBeforeAddToBag.replace("₹", "");
			int brandPriceBeforeAddToBaginInt = Integer.parseInt(brandPriceBeforeAddToBagRemoveRs);

//			Assert.assertEquals(brandNameBeforeAddToBag, brandNameOnHomePage);
//			Assert.assertEquals(priceOnHomePageInInt, brandPriceBeforeAddToBaginInt);

			if (brandNameBeforeAddToBag.contentEquals(brandNameOnHomePage)) {

				String getSizeText = "";
				int z = 1;
				do {

					try {
						String xpathExpression = String
								.format("//div[@class=\"size-buttons-tipAndBtnContainer\"][%d]/div/button/p", z);
						WebElement sizeButton = driver.findElement(By.xpath(xpathExpression));
						getSizeText = sizeButton.getText();
//						System.out.println(getSizeText);
						z++;
					} catch (Exception e) {
						System.out.println(z+ " : Use this value for Ittration of Loop");
						break;
					}

				} while (getSizeText != " ");

				for (int i = 1; i < z ; i++) {

					
//					String xpathExpression = String.format("//div[@class=\"size-buttons-tipAndBtnContainer\"][%d]/div/button/p", i);
					String xpathExpression = String.format("//div[@class=\"size-buttons-tipAndBtnContainer\"][%d]/div/button[contains(@class,'size-buttons')]", i);
					WebElement sizeButton = driver.findElement(By.xpath(xpathExpression));
					if (sizeButton.getAttribute("class").contains("disabled")) {
						String size = sizeButton.getText();
						System.out.println(" For brand "+brandNameBeforeAddToBag+ " , " + size +" size is NOT available. " );
						
					} else {
						String size = sizeButton.getText();
						System.out.println(" For brand "+brandNameBeforeAddToBag+ " , " + size +" size is available. " );
						
					}

				}

			} else {
				System.out.println("Brand Name is Diffrent Kindly check again");
			}
		}

	}

}
