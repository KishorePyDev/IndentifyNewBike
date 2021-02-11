package com.IndentifyNewBike.Test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.IndentifyNewBike.Base.Base;

public class ZigWheelsTest extends Base {
	
	@BeforeTest
	public void setup() throws InterruptedException 
	{
		invokeBrowser("chrome");
		openURL("websiteURL");
	}
	
	@Test
	public void validNewBikes() throws InterruptedException
	{
		elementHover("newBikes");
		
	}
	
	@Test(dependsOnMethods = "validNewBikes")
	public void validUpcommingBikes()
	{
		elementClick("btnupcomingBikes");
		scrollUntil("filterByBrand");
		elementClick("hondaBtn");
	}
	
	@Test(dependsOnMethods = "validUpcommingBikes")
	public void getBikeDetails() throws InterruptedException
	{

		scrollUntil("btnScroll");
		BikeUpcomingDetails();
		//elementClick("viewMore");

		
	}
	

	
	
	
	
}
