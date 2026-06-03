package seleniumCourse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
	// ThreadLocal to manage WebDriver instances per thread
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	// Volatile keyword ensures visibility of changes to the WebDriverSingleton
	// instance across threads
	private static volatile WebDriverSingleton instance;

	// Private constructor to prevent instantiation
	private WebDriverSingleton() {
	}

	/**
	 * Method to get the singleton instance of WebDriverSingleton. Uses
	 * double-checked locking for thread-safe initialization.
	 */
	public static WebDriverSingleton getInstance() {
		if (instance == null) { // First check without synchronization for performance
			synchronized (WebDriverSingleton.class) { // Class-level locking for thread safety
				if (instance == null) { // Second check inside synchronized block for proper lazy initialization
					instance = new WebDriverSingleton(); // Create the singleton instance
				}
			}
		}
		return instance;
	}

	/**
	 * Sets the WebDriver instance for the current thread based on the specified
	 * browser. If already set, it will return the existing instance.
	 */
	public WebDriver getDriver(String browser) {
		if (threadLocalDriver.get() == null) {
			// Call setDriver to create and set a WebDriver instance for the thread
			threadLocalDriver.set(setDriver(browser));
		}
		return threadLocalDriver.get(); // Return the WebDriver instance
	}

	/**
	 * Creates and configures a new WebDriver instance based on the specified
	 * browser.
	 */
	private WebDriver setDriver(String browser) {
		WebDriver driver;
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(); // Selenium 4+ auto-detects driver paths
			break;
		case "firefox":
			driver = new FirefoxDriver(); // Selenium 4+ auto-detects driver paths
			break;
		case "edge":
			driver = new EdgeDriver(); // Selenium 4+ auto-detects driver paths
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		driver.manage().window().maximize();
		return driver; // Return the newly created driver
	}

	/**
	 * Quits the WebDriver for the current thread and removes it from ThreadLocal
	 * storage.
	 */
	public void quitDriver() {
		WebDriver driver = threadLocalDriver.get();
		if (driver != null) {
			driver.quit(); // Quit the WebDriver instance
			threadLocalDriver.remove(); // Remove the WebDriver instance from ThreadLocal
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Get the singleton instance and set the WebDriver for the specified browser
        WebDriverSingleton webDriverSingleton = WebDriverSingleton.getInstance();// We can't create new instances.
        WebDriver driver = webDriverSingleton.getDriver("chrome"); // Use the getDriver method to fetch the driver

        // Perform actions with the WebDriver
        driver.get("https://example.com");
        System.out.println("Page title is: " + driver.getTitle());

        // Clean up the WebDriver
        webDriverSingleton.quitDriver(); // Use quitDriver to properly release resources
		
	}

}