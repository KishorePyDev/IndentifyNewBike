package com.IndentifyNewBike.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Base {

	public WebDriver driver;
	public Properties prop;

	/**
	 * @Author : Swetha
	 * @Date :8/02/2021 
	 * @Description: This function
	 *         implements the multiple broswers get the value from Test file
	 *         as paramaters
	 */
	public void invokeBrowser(String browserName) {

		if (browserName == "chrome") {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			driver = new ChromeDriver();
		}

		else if (prop.getProperty(browserName).equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/ObjectRepo/config.properties");
				prop.load(file);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public void openURL(String websiteURLKey) {
		driver.get(prop.getProperty(websiteURLKey));
	}

	public void elementClick(String xpathKey) {
		driver.findElement(By.xpath(xpathKey)).click();
	}

	public void elementHover(String linktextVal) {
		Actions action = new Actions(driver);
		;
		WebElement element = driver.findElement(By.linkText(linktextVal));
		action.moveToElement(element).build().perform();
	}
	/**
	 * @Author : Preethi
	 * @Date :10/02/2021 
	 * @Description: This function used scroll the webpage until particular element
	 *         
	 */
	public void scrollUntil(String scrollKey) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(scrollKey));
		je.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void uploadImage(String nameKey, String dataKey) {
		driver.findElement(By.name(prop.getProperty(nameKey))).sendKeys(prop.getProperty(dataKey));
	}

	public String getTitle() throws InterruptedException {

		return driver.getTitle();
	}

	public String aboutMessage(String id) throws InterruptedException {

		return driver.findElement(By.id(id)).getText();
	}

	/**
	 * @Author : Kishore Kumar S 
	 * @Date :16/02/2021 
	 * @Description: This function capture the screenshot of the webpage
	 *        
	 */
	public void screenShot(String fileName) throws InterruptedException {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(screenshot,
					new File("/home/kishore/eclipse-workspace/IndentifyNewBike/ScreenShots/"+ fileName+".png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * @Author : Kishore Kumar S 
	 * @Date :16/02/2021 
	 * @Description: This function capture the full screenshot of webpage
	 *        
	 */
	public void fullScreenShot(String fileName) {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		try {

			ImageIO.write(screenshot.getImage(), "PNG", new File(
					"/home/kishore/eclipse-workspace/IndentifyNewBike/ScreenShots/"+ fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<WebElement> linkCount(String tagName) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(tagName)));
		return driver.findElements(By.tagName(tagName));

	}

	public List<WebElement> listDetaails(String xpathKey) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(xpathKey)));
		return driver.findElements(By.xpath(xpathKey));

	}

	public List<WebElement> ListKey(String listKey) throws InterruptedException {

		List<WebElement> price = driver.findElements(By.xpath(listKey));

		return price;

	}

	public String extarctOfPopularModels(String popularModelKey) throws InterruptedException {

		String extractList = driver.findElement(By.xpath(popularModelKey)).getText();
		Reporter.log(extractList);
		return extractList;
	}
	
	/**
	 * @Author : Abila
	 * @Date :14/02/2021 
	 * @Description: This function used to handling the windows
	 *        
	 */
	public String logZig(String submitBtn) throws InterruptedException {
		String msg = null;

		String MainWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				Thread.sleep(2000);
				driver.findElement(By.id("identifierId")).sendKeys(prop.getProperty("emailID"));
				driver.findElement(By.xpath(submitBtn)).click();

				msg = driver.findElement(By.xpath(
						"//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div"))
						.getText();

				Reporter.log(msg);

			}
		}
		return msg;
	}

	public void tearDown() {
		driver.quit();
	}

}
