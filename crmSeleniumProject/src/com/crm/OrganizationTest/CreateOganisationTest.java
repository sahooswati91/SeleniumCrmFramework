package com.crm.OrganizationTest;


import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLib.BaseClass;
import com.crm.GenericLib.CommonUtils;
import com.crm.objectrepositoryLib.Contacts;
import com.crm.objectrepositoryLib.CreatingNewContacts;
import com.crm.objectrepositoryLib.CreatingNewOrganisation;
import com.crm.objectrepositoryLib.Home;
import com.crm.objectrepositoryLib.OrganisationInformation;
import com.crm.objectrepositoryLib.Organisations;

public class CreateOganisationTest extends BaseClass {
 @Test (groups={"regressiontest","smoketest"},priority=4)
	public static void createOganisationTest() throws Throwable {
	  Reporter.log("=======create Oganisation Test========",true);
		CommonUtils lib=new CommonUtils();
//		step:2 get data from excel file
		 Reporter.log("step:2 get data from excel file",true);
		String orgName=lib.returnData("Sheet1",4,4)+ran.nextInt();
//		step:3 navigate to organisation
		 Reporter.log("step:3 navigate to organisation",true);
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToOrganisation();
//		step:4 create new organisation
		 Reporter.log("step:4 create new organisation",true);
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
//		step:5 verification
		 Reporter.log("step:5 verification",true);
//		step:6 navigate to Organisation Information
		 Reporter.log("step:6 navigate to Organisation Information",true);
		OrganisationInformation orgInfoPage=PageFactory.initElements(driver, OrganisationInformation.class);;
		String actualOrganizationName=orgInfoPage.getOrganizationName().getText();
		SoftAssert sa1=new SoftAssert();
		sa1.assertEquals(actualOrganizationName.trim(),orgName.trim());
		Reporter.log("Organisation created successfully==pass",true);
	}
 @Test (groups="regressiontest",priority=5)
	public static void createOrganisationWithIndustryTest() throws Throwable {
	  Reporter.log("=======create Organisation With Industry Test========",true);	
	 CommonUtils lib=new CommonUtils();
//		step:2 get data from excel file
		 Reporter.log("step:2 get data from excel file",true);
		String orgName=lib.returnData("Sheet1",5,4)+ran.nextInt();
		String industryName=lib.returnData("Sheet1",5,9);
//		step:3 navigate to organisation
		 Reporter.log("step:3 navigate to organisation",true);
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToOrganisation();
//		step:4 create new organisation
		 Reporter.log("step:4 create new organisation",true);
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();;
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization(orgName,industryName);
//		step:5 verification
		 Reporter.log("step:5 verification",true);
//		step:6 navigate to organisation info page
		 Reporter.log("step:6 navigate to organisation info page",true);
		OrganisationInformation orgInfoPage=PageFactory.initElements(driver,OrganisationInformation.class);
		String actualorgName=orgInfoPage.getOrganizationName().getText();
		String actualIndustryName=orgInfoPage.getIndustryForOrganization().getText();
		SoftAssert sa2=new SoftAssert();
		boolean status=actualorgName.trim().equals(orgName)&&actualIndustryName.trim().equals("Banking");
		sa2.assertTrue(status);
		Reporter.log("organisation with banking type created successfully==pass",true);
		
   }


}
