package seleniumCourse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ShadowDOMTestsSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(url);
		WebElement content = driver.findElement(By.id("content"));

		SearchContext shadowRoot = content.getShadowRoot();
		WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
		Assert.assertEquals(textElement.getText(), "Hello Shadow DOM", "Text does not match!");
		System.out.println(textElement.getText());
		driver.quit();
	}

}
