package seleniumCourse;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class JavaStreamsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/seleniumPractise/#/offers";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();

		/* Check if the column is sorted in a table using Java Streams */

		// Click on table header to sort the column
		driver.findElement(By.xpath("//tr/th[1]")).click();
		// Grab the elements in the column and prepare a list
		List<WebElement> elementsList = driver.findElements(By.xpath("//tr/td[1]"));
		// Get text from each web element and prepare another list
		List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());
		// Sort the list
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
		// Check if original and sorted lists are matching or not
		Assert.assertTrue(originalList.equals(sortedList));

		/*
		 * Scan the name column->Grab the webelement of Beans->Fetch the price of Beans
		 */
		List<String> priceList = elementsList.stream().filter(s -> s.getText().contains("Beans"))
				.map(s -> getPriceVeggie(s)).collect(Collectors.toList());
		priceList.forEach(s -> System.out.println(s));

		/*
		 * Pagination - Scan the name column->Grab the webelement of Rice->Fetch the
		 * price of Rice which is in another page
		 */
		List<String> ricePriceList;
		do {
			List<WebElement> elementsList2 = driver.findElements(By.xpath("//tr/td[1]"));
			ricePriceList = elementsList2.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPriceVeggie(s))
					.collect(Collectors.toList());
			ricePriceList.forEach(s -> System.out.println(s));

			if (ricePriceList.size() < 1) {

				driver.findElement(By.cssSelector("[aria-label='Next']")).click();
			}
		} while (ricePriceList.size() < 1);

	}

	private static String getPriceVeggie(WebElement s) {
		// TODO Auto-generated method stub
		String price = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return price;
	}

}
