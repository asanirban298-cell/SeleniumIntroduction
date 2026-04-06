package seleniumCourse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class e2e {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/dropdownsPractise/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		// click One Way radio button
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
		// Select From - BLR and To - MAA cities
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//a[@value='BLR']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();// Selecting 2nd of two duplicate xpaths
		// Select Senior Citizen quota checkbox
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());// generic
																													// text
																													// in
																													// cssSelector
		// Select Departure date and assert return date field is disabled
		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();// cssSelector using 2 classes
		Thread.sleep(1000);
		if (driver.findElement(By.id("Div1")).getDomAttribute("style").contains("0.5"))// retrieved DOMAttribute 'Style'
		{
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		// Select number of passengers and assert correct selection
		driver.findElement(By.id("divpaxinfo")).click();

		for (int i = 1; i < 5; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}

		driver.findElement(By.id("btnclosepaxoption")).click();
		Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
		// Finally click Search button
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		Thread.sleep(4000);
		driver.close();

	}

}
