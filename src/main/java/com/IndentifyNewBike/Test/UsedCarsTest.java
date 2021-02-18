package com.IndentifyNewBike.Test;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.IndentifyNewBike.Base.Base;

public class UsedCarsTest extends Base 
{
	
	@BeforeGroups({"Smoke Suite","Regression Suite"})
	public void getUserDetails()
	{
		invokeBrowser("chrome");
		openURL("websiteURL");	
	}
	
	//TC9  Validate "Used Cars" tab
	@Test(groups= {"Smoke Suite","Regression Suite"},priority = 1)
	public void validUserCaars() throws InterruptedException
	{
		elementClick("//*[@id=\"headerNewNavWrap\"]/div[2]/ul/li[5]/a");
		Thread.sleep(4000);
		
	}
	
	//TC10 Validate the "Chennai" from list
	@Test(groups= {"Regression Suite"},priority = 2)
	public void validChennai()
	{
		elementClick("//*[@id=\"popularCityList\"]/li[8]/a");
	}
	
	//TC12 & 14 Extract Popular model list
	@Test(groups= {"Regression Suite"},priority = 3)
	public void extractcarDetails() throws InterruptedException
	{
		scrollUntil("/html/body/div[11]/div/div[1]/div[1]/div[1]/div[2]/ul/li[2]/div[1]/span[2]");
		String txt = extarctOfPopularModels("//div[@class='gsc_thin_scroll']");
		screenShot("PopularModels");
		System.out.println(txt);
		System.out.println("--------------------------");
		
		
	}
	
	@AfterGroups({"Smoke Suite","Regression Suite"})
	public void closeBrowser()
	{
		tearDown();
	}
	
	
	

}
