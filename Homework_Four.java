package com.Homwork;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Homework_Four extends BaseTest {

	/**
	 * Add to Cart Functionality Scenario: Verify that users can add items to their
	 * cart from the dashboard. TC : Select a product, click "Add to Cart", and
	 * check if it gets added to the cart successfully. Steps : 1.On dash board
	 * Select an Item to Add. 2.Add Item to Cart. 3.Verify Cart Update.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void VerifyThatUserCanAddItemToTheirCartFromTheDashboard() throws InterruptedException {

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
		scndTShirtPrice.click();

		String parantWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		handles.remove(parantWindow);
		for (String handle : handles) {

			driver.switchTo().window(handle);

			by = By.xpath("//h1[@class=\"pdp-title\"]");
			WebElement brandName = driver.findElement(by);
			String brandNameBeforeAddToBag = brandName.getText();

			Assert.assertEquals(brandNameBeforeAddToBag, brandNameOnHomePage);
			if (brandNameBeforeAddToBag.contentEquals(brandNameOnHomePage)) {

				by = By.xpath("//p[@class=\"size-buttons-unified-size\" and text() = \"L\"]");
				WebElement selectSize = driver.findElement(by);
				String selectedSize = selectSize.getText();
				selectSize.click();
				System.out.println(selectedSize);

				Thread.sleep(3000);

				by = By.xpath("//div[contains(@class,'pdp-add-to-bag')]/span");
				WebElement addToBagBtn = driver.findElement(by);
				addToBagBtn.click();
				System.out.println("Product has successfully added in bag");

				Thread.sleep(10000);

				by = By.xpath("//a[@class=\"desktop-cart\"]/child::span[1]");
				WebElement bag = driver.findElement(by);
				bag.click();
				System.out.println("Clicking on Bag");

				Thread.sleep(10000);

				by = By.xpath("//div[@class=\"secure\"]");
				WebElement cartPage = driver.findElement(by);
				String cartPageText = cartPage.getText();
				Assert.assertEquals(cartPageText, "100% SECURE");

				by = By.xpath("//div[@class=\"itemContainer-base-brand\"]");
				WebElement brndNameInPlaceOrderPage = driver.findElement(by);
				String brandNameInPlaceOrderPage = brndNameInPlaceOrderPage.getText();
				System.out.println("Got Brand Name on Cart Page");

				System.out.println("You have Added your product " + brandNameInPlaceOrderPage + " in Cart");

			} else {
				System.out.println("Brand Name is Diffrent Kindly check again");
			}

		}

	}
}
