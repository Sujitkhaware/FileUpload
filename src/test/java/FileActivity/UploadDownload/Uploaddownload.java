package FileActivity.UploadDownload;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Uploaddownload {

	public static void main(String[] args) {

		String fruitName = "Papaya";

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Download
		driver.findElement(By.id("downloadButton")).click();

		// Upload
		// What selenium does is when ever it views the type as file it shall mostly upload it.
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("F:\\Working Projects\\Selenium_Java\\Interview Questions\\Part_2\\download.xlsx");

		By notification = By.cssSelector(".Toastify__toast-body div:nth-child(2)");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(notification));

		String validateMessage = driver.findElement(notification).getText();

		System.out.println("Validation message = " + validateMessage);

		Assert.assertEquals("Updated Excel Data Successfully.", validateMessage);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(notification));

		// Child to parent, parenet to child.
		// Below is a dynamic xpath.
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		// String price = driver.findElement(By.xpath("//div[text()='Papaya']/parent::div/parent::div/div[@id='cell-4-undefined']/div")).getText();

		String price = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']/div")).getText();

		System.out.println("Price = " + price);

		Assert.assertEquals("2", price);

		driver.close();
	}
}