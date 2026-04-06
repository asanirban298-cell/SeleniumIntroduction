package seleniumCourse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaAlerts {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/AutomationPractice/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("name")).sendKeys("Anirban");
		driver.findElement(By.xpath("//input[@value='Alert']")).click();
		Thread.sleep(2000);
		//Get text from javascript alert window and accept
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.findElement(By.id("name")).sendKeys("Anirban");
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();
		Thread.sleep(2000);
		//Get text from javascript alert window and dismiss or cancel
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
		
	}

}
