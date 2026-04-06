package seleniumCourse;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class JavascriptExecutorDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/AutomationPractice/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		// Scrolling in page using JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)");
		Thread.sleep(2000);
		js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=5000");
		// Get the data in a certain column and verify their sum with the given total
		// amount
		List<WebElement> values = driver.findElements(By.xpath("//div[@class='tableFixHead'] //tr/td[4]"));
		int sum = 0;
		for (int i = 0; i < values.size(); i++) {

			sum = sum + Integer.parseInt(values.get(i).getText());

		}
		System.out.println(sum);

		int totalAmount = Integer
				.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());

		Assert.assertEquals(sum, totalAmount);

		// Same logic for another Webtable
		List<WebElement> priceList = driver.findElements(By.xpath("//table[@name='courses'] //tr/td[3]"));
		int totalPrice = 0;
		for (int i = 0; i < priceList.size(); i++) {

			totalPrice = totalPrice + Integer.parseInt(priceList.get(i).getText());

		}
		System.out.println(totalPrice);

	}

}
