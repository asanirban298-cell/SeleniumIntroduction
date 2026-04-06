package seleniumCourse;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/loginpagePractise/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".blinkingText")).click();
		Set<String> wh = driver.getWindowHandles();// driver gets all the windowIDs opened
		Iterator<String> it = wh.iterator();// Iterator created to iterate over the WindowIDs Set
		String parentID = it.next();// it goes to parent windowID
		String childID = it.next();// it goes to child windowID
		driver.switchTo().window(childID);// driver switches to child window
		System.out.println(driver.findElement(By.cssSelector(".im-para.red")).getText());// whole text is retrieved
		String emailID = driver.findElement(By.cssSelector(".im-para.red")).getText().split(" at ")[1].split(" ")[0];
		driver.switchTo().window(parentID);
		driver.findElement(By.id("username")).sendKeys(emailID);

	}

}
