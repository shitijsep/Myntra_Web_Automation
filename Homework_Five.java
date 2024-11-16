package com.Homwork;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Five extends BaseTest {

	@Test
	public void VerifyThatAfterClickingOnNavyBlueChcekBoxItIsComimgWithFilterOrNOt() throws InterruptedException {

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

		by = By.xpath("(//label[@class=\"common-customCheckbox\"])[4]");
		WebElement navyBlueCheckBox = driver.findElement(by);
		navyBlueCheckBox.click();
		System.out.println("Navy Blue Check Box present and clicking");
		
		Thread.sleep(3000);
		
		by = By.xpath("((//label[@class=\"common-customCheckbox\"])[4]/span)[1]");
		WebElement navyblueCheckBoxtext = driver.findElement(by);
		String navyBlueCheckBoxtext = navyblueCheckBoxtext.getText();
		System.out.println("This is text on Navy Blue CheckBox : "+navyBlueCheckBoxtext);
		
		by = By.xpath("//div[@class=\"filter-summary-filter\"]");
		WebElement navyBlueTag = driver.findElement(by);
		System.out.println("As filter Tag Has added. ");

		
		by = By.xpath("//div[@class=\"filter-summary-filter\"]/span/span");
		WebElement navybluetagtext = driver.findElement(by);
		String navyBlueTagText = navybluetagtext.getText();
		System.out.println(" This is text on Navy Blue Tag Text : "+navyBlueTagText);
		
		Assert.assertEquals(navyBlueCheckBoxtext, navyBlueTagText);
		
	}

}
