package seleniumCourse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://www.amazon.com/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		Actions ac = new Actions(driver);
		// Move to textbox element, type data in CAPS and select the data
		ac.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello")
				.doubleClick().build().perform();
		// Moves to specific element - MouseHover action
		WebElement hover = driver.findElement(By.xpath("//div[@id='nav-link-accountList']"));
		ac.moveToElement(hover).build().perform();
		Thread.sleep(1000);
		ac.moveToElement(hover).contextClick().build().perform();
		//

	}

}
