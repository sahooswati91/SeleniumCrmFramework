package com.Com.GenericLib;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.Com.GenericLib.CommonUtils;
import com.crm.objectrepositoryLib.Common;
import com.crm.objectrepositoryLib.LogIn;

public class BaseClass {
	public static WebDriver driver;
	public static Random ran=new Random();
//	@Parameters("browser")
	@BeforeClass(groups={"regressiontest","smoketest"})
	public void configLunchBrowser() throws Throwable
	{
//		lunch the browser
		CommonUtils lib=new CommonUtils();
		Properties p=lib.getPropertyDataObj();
		String pBrowser=p.getProperty("browser");
		if(pBrowser.equals("firefox"))
		{
		driver=new FirefoxDriver();
		}
		else if(pBrowser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","./resourse/chromedriver.exe" );
			driver=new ChromeDriver();
		}
		else if(pBrowser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver","./resourse/IEDriverServer.exe" );
			driver=new InternetExplorerDriver();
		}
	}
	@BeforeMethod(groups={"regressiontest","smoketest"})
	public void configLogin() throws Throwable
	{
		driver.manage().window().maximize();
//		create object of commonutils class
		System.out.println("in configLogin");
		CommonUtils lib=new CommonUtils();
		Properties p=lib.getPropertyDataObj();
//		get data from properties file
		String url=p.getProperty("url");
		String username=p.getProperty("userName");
		String password=p.getProperty("passWord");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
//		Step:1 log in to application
		LogIn loginpage=PageFactory.initElements(driver, LogIn.class);
		loginpage.logIn(username, password);
		}
	@AfterMethod(groups={"regressiontest","smoketest"})
	public void configLogout() throws Throwable
	{
		Common commonPage=PageFactory.initElements(driver,Common.class);
		commonPage.logOut();
	}
	@AfterClass(groups={"regressiontest","smoketest"})
	public void configBrowserClose()
	{
		while(true)
		{
			try
			{
		driver.close();
		System.out.println("driver closed successfully");
		break;
			}
			catch(Throwable t)
			{
				
			}
		}
	}
	
}
