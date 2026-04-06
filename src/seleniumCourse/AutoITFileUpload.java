package seleniumCourse;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

public class AutoITFileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String downloadPath = System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://smallpdf.com/pdf-to-jpg");

		driver.findElement(By.xpath("//span[contains(text(),'Choose Files')]")).click();
		Thread.sleep(3000);

		// This is a deprecated method to handle AutoIT Selenium validation
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "\\AutoITScripts\\FileUpload.exe");

		String autoItPath = System.getProperty("user.dir") + "\\src\\seleniumCourse\\AutoITScripts\\FileUpload.exe";
		ProcessBuilder pb = new ProcessBuilder(autoItPath);
		pb.start();

		driver.findElement(By.xpath("//span[contains(text(),'Convert')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Download')]")).click();
		Thread.sleep(5000);
		File f = new File(downloadPath + "/converted.zip");

		if (f.exists())

		{

			Assert.assertTrue(f.exists());

			if (f.delete())

				System.out.println("file deleted");

		}

		// driver.close();

	}

}
