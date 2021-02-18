package com.IndentifyNewBike.Test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	public void captureMessage() throws InterruptedException, IOException
	{
		String txt = logZig("//*[@id='identifierNext']/div/button/div[2]");
		screenShot("loginError");
		System.out.println(txt);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("ErrorMessage");
		XSSFRow row = sheet.createRow(0);
		for(int i=0;i<1;i++)
		{
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(txt);
			
		}
		FileOutputStream os = new FileOutputStream("CaptureMessage.xlsx");
		wb.write(os);
		wb.close();
		
		
		
		System.out.println("--------------------------");
	}
	
	
	public void closeBrowser()
	{
		tearDown();
	}

}
