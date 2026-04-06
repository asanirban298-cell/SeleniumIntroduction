package seleniumCourse;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Scope {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/AutomationPractice/";
		WebDriver driver = new ChromeDriver();
		Actions ac = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		// Finding number of links under footer in page - using xpath
		// parent-child relationship
		System.out.println(driver.findElements(By.xpath("//div[contains(@class,'footer')] //a")).size());
		// Finding number of links under footer in page - partial/limiting
		// WebDriver scope
		WebElement footerDriver = driver.findElement(By.xpath("//div[contains(@class,'footer')]"));
		System.out.println(footerDriver.findElements(By.tagName("a")).size());
		// Finding number of links under certain column in footer in page - using xpath
		// parent-child relationship
		System.out.println(footerDriver.findElements(By.xpath("//tr/td[1] //a")).size());
		// Finding number of links under certain column in footer in page -
		// partial/limiting
		// WebDriver scope
		WebElement footerColumnDriver = footerDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
		System.out.println(footerColumnDriver.findElements(By.tagName("a")).size());
		// Click on each link and check if the pages are opening
		List<WebElement> footerLinks = footerColumnDriver.findElements(By.tagName("a"));
		for (int i = 0; i < footerLinks.size(); i++) {

			// Using Actions class
			// ac.moveToElement(footerLinks.get(i)).keyDown(Keys.COMMAND).click().build().perform();

			// Using keyboard events and sendKeys
			String clickLinkNewTab = Keys.chord(Keys.COMMAND, Keys.ENTER);
			footerLinks.get(i).sendKeys(clickLinkNewTab);

		}
		Thread.sleep(10000);
		Set<String> wh = driver.getWindowHandles();
		if (wh.size() == footerLinks.size() + 1)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		System.out.println(wh.size());

		// Getting the titles of each open tab
		Iterator<String> it = wh.iterator();
		while (it.hasNext()) {

			driver.switchTo().window(it.next());
			Thread.sleep(1000);
			System.out.println(driver.getTitle());

		}

	}

}
