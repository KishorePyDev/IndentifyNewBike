package com.IndentifyNewBike.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.IndentifyNewBike.Base.Base;

public class loginTest extends Base 
{
	
	@BeforeTest
	public void getUserDetails()
	{
		invokeBrowser("chrome");
		openURL("websiteURL");	
	}
	
	@Test
	public void googleLogin() throws InterruptedException
	{
		logZig();
	}

}
