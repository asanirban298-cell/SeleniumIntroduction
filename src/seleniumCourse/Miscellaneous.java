package seleniumCourse;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class Miscellaneous {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/dropdownsPractise/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();// Maximize browser
		driver.manage().deleteAllCookies();// Delete all cookies
		driver.get(url);

		// Take screenshot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("/Users/anirbansarkar/Documents/PERSONAL/UDEMY - SELENIUM/screenshot.png"));

	}

}
