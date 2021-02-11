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

public class Base {

	public WebDriver driver;
	public Properties prop;

	/**
	 * @Author : Kishore Kumar S Date :26/01/2021 Description: This function
	 *         implements the multiple broswers get the value from testing.xml file
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
		driver.findElement(By.xpath(prop.getProperty(xpathKey))).click();
	}
	
	
	public void elementHover(String linktextVal)
	{
		Actions action = new Actions(driver);;
		WebElement element = driver.findElement(By.linkText(prop.getProperty(linktextVal)));
		action.moveToElement(element).build().perform();
	}
	
	public void scrollUntil(String scrollKey)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(prop.getProperty(scrollKey)));
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
	 * @Author : Kishore Kumar S Date :26/01/2021 Description: This function capture
	 *         the screenshot of webpage
	 */
	public void screenShot() throws InterruptedException {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(screenshot,
					new File("/home/kishore/eclipse-workspace/SearchByUploadImage/ScreenShots/searchResult.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<WebElement> linkCount(String tagName) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(tagName)));
		return driver.findElements(By.tagName(tagName));

	}
	
	public List<WebElement> listDetaails(String xpathKey) throws InterruptedException 
	{

		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(xpathKey)));
		return driver.findElements(By.xpath(xpathKey));

	}
	
	public void BikeUpcomingDetails() throws InterruptedException
	{
		List<WebElement> price = driver.findElements(By.xpath("//div[@class='clr-bl p-5']"));
		List<WebElement> bname = driver.findElements(By.xpath("//strong[@class='lnk-hvr block of-hid h-height']"));
		List<WebElement> ldate = driver.findElements(By.xpath("//div[@class='clr-try fnt-15']"));
		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='carModels']/ul/li[21]/span")).click();
	
		Thread.sleep(5000);
		

		List<WebElement> priceElement =  price;
		List<WebElement> nameElement =  bname;
		List<WebElement> launchdate =  ldate;

		
		Map<Double, String> objMap = new HashMap<Double, String>();

		List<String> bikeDetails = new ArrayList<String>();
	

		for (int i = 0; i < price.size(); i++) 
		{
			
			String priceList = priceElement.get(i).getText();
			String nameList = nameElement.get(i).getText();
			String lauchDatelist = launchdate.get(i).getText();
	
			Double val = Double.parseDouble(priceList.substring(4,9));

			objMap.put(val, nameList+" "+lauchDatelist);
		

		}

        TreeMap<Double, String> sorted = new TreeMap<Double, String>(); 

        sorted.putAll(objMap); 
        
        String lineSeparator= System.getProperty("line.separator");

        for (Map.Entry<Double, String> entry : sorted.entrySet())  
        {

            if(entry.getKey() <= 4.00)
            {
            	 Reporter.log("Rs. "+ entry.getKey()+" lakh" + lineSeparator +
                         entry.getValue());
            	 Reporter.log("");
            	 
            	
            }
        }
	}
	
	public void extarctOf() throws InterruptedException
	{

		String extractList = driver.findElement(By.xpath("//div[@class='gsc_thin_scroll']")).getText();
		Reporter.log(extractList);
		
	}
	
	
	public void logZig() throws InterruptedException
	{
		// Window Handling Concept
		driver.findElement(By.id("forum_login_title_lg")).click();


		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='googleSignIn']")).click();
		
		// line 221 to 236
		 String MainWindow=driver.getWindowHandle();
		 
 		
	        // To handle all new opened window.				
	        Set<String> s1=driver.getWindowHandles();
	        Iterator<String> i1=s1.iterator();		
	        		
	        while(i1.hasNext())			
	        {		
	            String ChildWindow=i1.next();		
	            		
	            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
	            {    		
	                 
                // Switching to Child window
                driver.switchTo().window(ChildWindow);	 
                Thread.sleep(2000);
                driver.findElement(By.id("identifierId"))
                .sendKeys("dummy@gmal.com");     
                driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/div[2]")).click();
                
                String msg = driver.findElement(By.xpath("//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div")).getText();
                
               Reporter.log(msg);	
                
                driver.close();
	                                 
				
	                     		
	            }		
	        }		
	}


	public void tearDown() {
		driver.close();
	}

}
