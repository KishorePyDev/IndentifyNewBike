package com.IndentifyNewBike.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.IndentifyNewBike.Base.Base;

public class ZigWheelsTest extends Base {


	
	//@BeforeTest()
	@BeforeGroups({"Smoke Suite","Regression Suite"})
	public void setup() throws InterruptedException 
	{
		invokeBrowser("chrome");
		openURL("websiteURL");
	}

	//TC1  Validate the "new bikes" tab
	@Test(groups= {"Smoke Suite","Regression Suite"},priority = 1)
	public void validNewBikes() throws InterruptedException 
	{
		elementHover("New Bikes");

	}

	//TC2 validate the "Upcomming bikes" tab
	@Test(groups= {"Smoke Suite","Regression Suite"},priority = 2)
	//@Test(dependsOnMethods = "validNewBikes")
	//@Test(groups="Smoke Suite")
	public void validUpcommingBikes() 
	{
		elementClick("//*[@id=\"headerNewNavWrap\"]/div[2]/ul/li[3]/ul/li/div[1]/ul/li[3]/span");
		
	}
	
	//TC4 Validate the Honda Filter
	@Test(groups= {"Regression Suite"},priority = 3)
	public void validateHonda()
	{
		scrollUntil("//*[@id=\"carModels\"]/ul/li[9]/div");
		elementClick("//*[@id=\"carModels\"]/ul/li[9]/div/div/div/div/div/ul/li[2]/a");
		

	}

	//TC5 & 7 Display the Bike name,price,Launch Date  
	@Test(groups= {"Regression Suite"},priority = 4)
	public void getBikeDetails() throws InterruptedException, IOException 
	{
	
		
		scrollUntil("//*[@id='carModels']/ul/li[20]/span");
		List<WebElement> price = ListKey("//div[@class='clr-bl p-5']");
		List<WebElement> bname = ListKey("//strong[@class='lnk-hvr block of-hid h-height']");
		List<WebElement> ldate = ListKey("//div[@class='clr-try fnt-15']");

		Thread.sleep(5000);
		elementClick("//*[@id='carModels']/ul/li[20]/span");


		List<WebElement> priceElement = price;
		List<WebElement> nameElement = bname;
		List<WebElement> launchdate = ldate;

		Map<Double, String> objMap = new HashMap<Double, String>();

		List<String> bikeDetails = new ArrayList<String>();

		for (int i = 0; i < price.size(); i++) {

			String priceList = priceElement.get(i).getText();
			String nameList = nameElement.get(i).getText();
			String lauchDatelist = launchdate.get(i).getText();

			Double val = Double.parseDouble(priceList.substring(4, 9));

			objMap.put(val, nameList + " " + lauchDatelist);

		}

		TreeMap<Double, String> sorted = new TreeMap<Double, String>();

		sorted.putAll(objMap);

		String lineSeparator = System.getProperty("line.separator");

		for (Map.Entry<Double, String> entry : sorted.entrySet()) {

			if (entry.getKey() <= 4.00) {
				Reporter.log("Rs. " + entry.getKey() + " lakh" + lineSeparator + entry.getValue());
				System.out.println("Rs. " + entry.getKey() + " lakh" + "" + entry.getValue());
				
				

			}
		}
		fullScreenShot("UpcomingBikeList");
		System.out.println("--------------------------");
	          
		}
	
	
	@AfterGroups({"Smoke Suite","Regression Suite"})
	public void closeBrowser()
	{
		tearDown();
	}

}
