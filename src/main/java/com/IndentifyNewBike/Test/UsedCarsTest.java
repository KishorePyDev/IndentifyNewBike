package com.IndentifyNewBike.Test;

import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.IndentifyNewBike.Base.Base;

public class UsedCarsTest extends Base 
{
	
	@BeforeTest
	public void getUserDetails()
	{
		invokeBrowser("chrome");
		openURL("websiteURL");	
	}
	
	@Test
	public void validUserCaars() throws InterruptedException
	{
		elementClick("usedCarsBtn");
		elementClick("chennaiBtn");
		Thread.sleep(4000);
		
	}
	
	@Test(dependsOnMethods = "validUserCaars")
	public void extractcarDetails() throws InterruptedException
	{
		scrollUntil("brandModel");
	
		extarctOf();
		tearDown();
		
	}

}
