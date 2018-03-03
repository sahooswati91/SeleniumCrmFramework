package com.crm.GenericLib;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class SampleListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String  testName=result.getMethod().getMethodName();
		System.out.println("inside on testfailure");
		EventFiringWebDriver eDriver=new EventFiringWebDriver(BaseClass.driver);
		
        File sourcFile=eDriver.getScreenshotAs(OutputType.FILE);
        
        File dstFile=new File("./screenShot"+testName+".png");
        try{
        FileUtils.copyFile(sourcFile, dstFile);
        }
        catch(IOException i)
        {
        	
        }
		
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	
		
		

	}


