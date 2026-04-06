package seleniumCourse;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/seleniumPractise/#/offers";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.manage().window().maximize();

		String targetMonth = "11"; // November (index-based, 0=Jan, so 11 = December)
		String targetYear = "1990";
		String targetDate = "6";

		driver.findElement(By.cssSelector(".react-date-picker__calendar-button__icon")).click();

		driver.findElement(By.className("react-calendar__navigation__label__labelText")).click(); // to decade view

		driver.findElement(By.className("react-calendar__navigation__label__labelText")).click(); // to century view

		while (true) {

			List<WebElement> visibleYears = driver.findElements(By.cssSelector(".react-calendar__tile"));

			boolean yearFound = false;

			for (WebElement y : visibleYears) {

				if (y.getText().equals(targetYear)) {

					y.click();

					yearFound = true;

					break;

				}

			}

			if (yearFound)
				break;

			int displayedStartYear = Integer.parseInt(visibleYears.get(0).getText());

			int displayedEndYear = Integer.parseInt(visibleYears.get(visibleYears.size() - 1).getText());

			if (Integer.parseInt(targetYear) > displayedEndYear) {

				driver.findElement(By.cssSelector(
						"button.react-calendar__navigation__arrow.react-calendar__navigation__next-button")).click();

			} else {

				driver.findElement(By.cssSelector(
						"button.react-calendar__navigation__arrow.react-calendar__navigation__prev-button")).click();

			}

		}

		driver.findElements(By.cssSelector(".react-calendar__year-view__months__month"))

				.get(Integer.parseInt(targetMonth) - 1).click();

		driver.findElement(By.xpath(
				"//button[contains(@class,'react-calendar__month-view__days__day')][not(contains(@class,'neighboringMonth'))]/abbr[text()='"
						+ targetDate + "']"))
				.click();
	}

}
