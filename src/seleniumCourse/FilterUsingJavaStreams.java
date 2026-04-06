package seleniumCourse;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class FilterUsingJavaStreams {

	public static void main(String args[]) {
		String url = "https://rahulshettyacademy.com/seleniumPractise/#/offers";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		/*
		 * Search for Rice and check the items displayed only contain Rice
		 */

		// driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Rice");
		List<WebElement> elementsList = driver.findElements(By.xpath("//tr/td[1]"));
		List<WebElement> filteredList = elementsList.stream().filter(s -> s.getText().contains("Rice"))
				.collect(Collectors.toList());
		Assert.assertEquals(elementsList.size(), filteredList.size());

	}

}
