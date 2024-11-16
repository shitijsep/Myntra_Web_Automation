package com.Homwork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_Eight extends BaseTest {

	/**
	 * Ensure that count of Men T-shirts is same as image of T-shirts are present.
	 * 1.Open the Myntra website. 2.take the count of Men Men Kurtas from the
	 * dashboard. 3.check the total number of images present under result. 4.Ensure
	 * that count and images should have same number.
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void VerifyCountOfProduct() throws InterruptedException {

		Actions act = new Actions(driver);
		By by = By.xpath("(//a[@href=\"/shop/men\"])[1]");
		WebElement menSection = driver.findElement(by);
		act.moveToElement(menSection).perform();

		Thread.sleep(3000);

		by = By.xpath("//a[text() = \"Kurtas & Kurta Sets\"]");
		WebElement kurtasKurtaSets = driver.findElement(by);
		kurtasKurtaSets.click();

		Thread.sleep(3000);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");

		Thread.sleep(4000);

		by = By.xpath("//ul[@class=\"discount-list\"]/li[8]/label");
		WebElement discountInPercentage = driver.findElement(by);
		discountInPercentage.click();

		Thread.sleep(3000);

		by = By.xpath("//div[@class=\"title-container\"]/h1[text() = \"Men Kurtas\"]");
		WebElement menkurta = driver.findElement(by);
		String menkurtaText = menkurta.getText();
		System.out.println(menkurtaText);

		by = By.xpath("//div[@class=\"title-container\"]/span[@class=\"title-count\"]");
		WebElement kurtasTitelCount = driver.findElement(by);
		String kurtasTitelCountText = kurtasTitelCount.getText();
		System.out.println("Total numbers of Items are" + kurtasTitelCountText);

		String kurtasTitelCountTextInNumber = kurtasTitelCountText.substring(3, 6);
		System.out.println("Total numbers of Items are " +kurtasTitelCountTextInNumber);
		int kurtasTitelCountTextInNumberInInteger = Integer.parseInt(kurtasTitelCountTextInNumber);
		//		 - 27248 items

		int producTitelCount = 0, producTitelNotFound = 0, nextButtonClick = 0;
		String nextButtonText = " ";
		do {
			Thread.sleep(3000);
			by = By.xpath("//div[@class=\"product-productMetaInfo\"]/h4[@class=\"product-product\"]");
			List<WebElement> productnames = driver.findElements(by);

			for (int i = 0; i < productnames.size(); i++) {

				String productname = productnames.get(i).getText();
				System.out.println(productname);

				if (productname.contains("Kurta")) {
					producTitelCount++;
					System.out.println("ProducTitel Count "+producTitelCount);
				}else {
					producTitelNotFound++;
					System.err.println("ProducTitelNotFound "+producTitelNotFound);
				}

			}
			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"results-showMoreContainer\"]/ul/li[text() = \"Next\"]")));
			} catch (Exception e) {
				break;
			}
			WebElement nextButton = driver.findElement(By.xpath("//div[@class=\"results-showMoreContainer\"]/ul/li[text() = \"Next\"]"));
			nextButton.click();
			nextButtonText = nextButton.getText();
			nextButton.click();
			nextButtonClick++;
			System.err.println("Next Button Click " +nextButtonClick);

			Thread.sleep(3000);
		

		} while (nextButtonText.contains("Next"));

		Assert.assertEquals(kurtasTitelCountTextInNumberInInteger, producTitelCount);
		
	}

}
