package com.IndentifyNewBike.Test;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.IndentifyNewBike.Base.Base;

public class loginTest extends Base 
{
	
	   @Parameters("browserName")
		@BeforeGroups({"Smoke Suite","Regression Suite"})
		public void setup(String browserName) throws InterruptedException 
		{
			invokeBrowser(browserName);
			openURL("websiteURL");
		}
	
	// Validate the login button
	@Test(groups= {"Smoke Suite","Regression Suite"},priority = 1)
	public void validLoginButton() throws InterruptedException
	{

		elementClick("//*[@id=\"forum_login_title_lg\"]");
	}
	
	//validate continue with Google Signin button
	@Test(groups= {"Regression Suite"},priority = 2)
	public void validGoogleLoginButton() throws InterruptedException
	{
		Thread.sleep(5000);
		elementClick("//*[@id='googleSignIn']");
	}
	
	//Display error message
	@Test(groups= {"Regression Suite"},priority = 3)
	public void captureMessage() throws InterruptedException
	{
		String txt = logZig("//*[@id='identifierNext']/div/button/div[2]");
		screenShot("loginError");
		System.out.println(txt);
		System.out.println("--------------------------");
	}
	
	
	public void closeBrowser()
	{
		tearDown();
	}

}
