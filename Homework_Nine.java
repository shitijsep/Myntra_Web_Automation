package com.Homwork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Nine extends BaseTest {

	/**
	 * Scenario: Apply Size Filter 1. Search for a product 2. Apply size filter
	 * @throws InterruptedException 
	 */

	@Test
	public void VerifyApplyingSizeFilter() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("//a[text() = \"Kurtas & Kurta Sets\"]");
		WebElement kurtasKurtaSets = driver.findElement(by);
		kurtasKurtaSets.click();

		Thread.sleep(4000);

		by = By.xpath("//div[@class=\"atsa-more\"]");
		WebElement pluse22MoreOPtion = driver.findElement(by);
		pluse22MoreOPtion.click();
		
		Thread.sleep(4000);
		
		by = By.xpath("//div[@class=\"atsa-base\"]/ul/li/label/h4[text() = \"Size\"]");
		WebElement sizeOPtion = driver.findElement(by);
		sizeOPtion.click();
		
		Thread.sleep(5000);
		
		by = By.xpath("//div[@id=\"desktopSearchResults\"]/descendant::ul[@class=\"atsa-values\"]/child::li[8]/child::label[@class=\"common-customCheckbox\"]/child::div");
		WebElement lsizeOPtion = driver.findElement(by);
		lsizeOPtion.click();
		
		Thread.sleep(5000);
		
		
		by = By.xpath("//div[@id=\"desktopSearchResults\"]/child::div[@class=\" row-base\"]/section/child::div[2]/ul/li/div[@class=\"filter-summary-filter\"]");
		WebElement isLrgSummryFilterListSelected = driver.findElement(by);
		String isLrgSummryFilterListSelectedText = isLrgSummryFilterListSelected.getText();
		Assert.assertEquals(isLrgSummryFilterListSelectedText, "L");
			
		System.out.println("Nice");
		
		by = By.xpath("//h4[@class=\"product-sizes\"]");
		List<WebElement> productSizeInventoryPresent = driver.findElements(by);
		
		for (int i = 0; i < productSizeInventoryPresent.size(); i++) {
			
//loop is for productSizeInventoryPresents size printing but we are not getting msg printed.			
			String productSizeInventoryPresents = productSizeInventoryPresent.get(i).getText();	
			System.out.println(productSizeInventoryPresents);
			System.out.println("Till here i reach");
			
		}
		
		
		
	}

}
