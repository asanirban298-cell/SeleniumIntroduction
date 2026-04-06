package seleniumCourse;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddItemsToCart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/seleniumPractise/#/";
		String itemsNeeded[] = { "Brocolli", "Potato", "Beans", "Cashews" };
		WebDriver driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Implicit Wait
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(url);

		addItems(driver, itemsNeeded);
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]")).click();
		// Explicit Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		// Explicit Wait
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());

		// driver.close();

	}

	public static void addItems(WebDriver driver, String[] itemsNeeded) {

		// Approach using Custom Xpath

//		for (int i = 0; i < order.length; i++) {
//
//			driver.findElement(By.xpath(
//					"//h4[contains(text(),'" + itemsNeeded[i] + "')]/following-sibling::div/button[text()='ADD TO CART']"))
//					.click();
//
//		}

		// Approach using cssSelector and storing items in List/ArrayList

		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for (int i = 0; i < products.size(); i++) {

			String name[] = products.get(i).getText().split(" - ");
			String veggie = name[0];

			List<String> itemsNeededList = Arrays.asList(itemsNeeded);

			int counter = 0;
			if (itemsNeededList.contains(veggie)) {

				counter++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (counter == itemsNeeded.length)
					break;

			}

		}

	}

}
