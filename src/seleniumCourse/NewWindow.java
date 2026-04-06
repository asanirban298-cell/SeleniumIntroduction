package seleniumCourse;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class NewWindow {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/angularpractice/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		// Invoke new window or tab
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://rahulshettyacademy.com/");
		Set<String> wh = driver.getWindowHandles();
		Iterator<String> it = wh.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
		driver.switchTo().window(parentID);

		// Taking partial screenshot of a particular WebElement
		WebElement name = driver.findElement(By.cssSelector("[name='name']"));
		File file = name.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("/Users/anirbansarkar/Documents/PERSONAL/UDEMY - SELENIUM/screenshot.png"));

		// Printing the height and width of a certain WebElement
		System.out.println(name.getRect().getHeight());
		System.out.println(name.getRect().getWidth());

	}

}
