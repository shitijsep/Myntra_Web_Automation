package com.Homwork;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Six extends BaseTest{

	/*
	 * Scenario: Verify that users can remove items from the cart.
	 */
	
	@Test
	public void verifyThatUsersCanRemoveItemsFromTheCart() throws InterruptedException {

		
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

				by = By.xpath("(//div[@class=\"pdp-description-container\"]/div[2]/div[3]/div/div)[1]");
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

				Thread.sleep(3000);
				
				by = By.xpath("//div[@class=\"bulkActionStrip-selectionIcon\"]	");
				WebElement itemForSelectedCheckbox = driver.findElement(by);
				itemForSelectedCheckbox.click();
			
				Thread.sleep(3000);
				
				by = By.xpath("//span[@class=\"bulkActionStrip-itemSelected\"]");
				WebElement itemSelectd = driver.findElement(by);
				String itemSelectdText = itemSelectd.getText();
				String zeroValueInItemSelected = (String) itemSelectdText.subSequence(0, 2);
				System.out.println(zeroValueInItemSelected);
				Assert.assertEquals(zeroValueInItemSelected, "0/");
				
				if(brandNameOnHomePage.equalsIgnoreCase(brandNameInPlaceOrderPage) 	) {
					
					by = By.xpath("(//div[@class=\"itemComponents-base-selectionIconContainer itemContainer-base-selectionIndicator\"])[1]");
					WebElement itemCheckbox = driver.findElement(by);
					
					Thread.sleep(5000);
					
					itemCheckbox.click();	
					
					Thread.sleep(5000);
					by = By.xpath("//button[@class=\"inlinebuttonV2-base-actionButton bulkActionStrip-desktopBulkRemove\"]");
					WebElement removeButton = driver.findElement(by);
					removeButton.click();
					
					by = By.xpath("(//div[@class=\"inlinebuttonV2-base-action  confirmOrCancelModal-buttonClass\"])[1]");
					WebElement removeButtononAleart = driver.findElement(by);
					removeButtononAleart.click();
					
					Thread.sleep(5000);
					
					by = By.xpath("//div[@class=\"emptyCart-base-emptyDesc\"]");
					WebElement msgOnBagPage = driver.findElement(by);
					String txtMSGOnBagPage = msgOnBagPage.getText();
					
					Assert.assertEquals(txtMSGOnBagPage, "There is nothing in your bag. Let's add some items.");
					
					Thread.sleep(5000);
					
				}
				
				

				
				
				
			} else {
				System.out.println("Brand Name is Diffrent Kindly check again");
			}

		}

	}
	
	
}
