package seleniumCourse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	@SuppressWarnings("resource")
	private static int getColumnNumber(String filePath, String columnName) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cells = firstRow.cellIterator();
		int k = 1;
		int column = 0;
		while (cells.hasNext()) {
			Cell value = cells.next();
			if (value.getStringCellValue().equalsIgnoreCase(columnName)) {

				column = k;
				break;

			}
			k++;
		}

		return column;

	}

	@SuppressWarnings("resource")
	private static int getRowNumber(String filePath, String value) throws IOException {

		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		int rowValue = 0;
		for (int i = 0; i < rowCount - 1; i++) {

			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {

				XSSFCell cell = row.getCell(j);
				if (formatter.formatCellValue(cell).contains(value)) {

					rowValue = i + 1;

				}

			}

		}
		return rowValue + 1;

	}

	private static boolean updateCell(String filePath, int row, int column, String updatedValue) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		Row rowfield = sheet.getRow(row - 1);
		Cell cellField = rowfield.getCell(column - 1);
		cellField.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fis.close();
		return true;

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fruitName = "Apple";
		String colName = "Price";
		String updatedValue = "400";
		String fileInputPath = "//Users//anirbansarkar//download.xlsx";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		// download

		driver.findElement(By.cssSelector("#downloadButton")).click();

		// Edit excel - getColumnNumber of Price -getRownumber of APple-> update excel
		// with row,col

		int col = getColumnNumber(fileInputPath, colName);
		int row = getRowNumber(fileInputPath, fruitName);
		Assert.assertTrue(updateCell(fileInputPath, row, col, updatedValue));

		// upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("/Users/anirbansarkar/download.xlsx");

		// wait for success message to show up and wait for disappear
		By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		String toastText = driver.findElement(toastLocator).getText();
		System.out.println(toastText);
		Assert.assertEquals("Updated Excel Data Successfully.", toastText);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));

		// verify updated excel data showing in the web table
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
		System.out.println(actualPrice);
		Assert.assertEquals(updatedValue, actualPrice);
		driver.close();

	}
}
