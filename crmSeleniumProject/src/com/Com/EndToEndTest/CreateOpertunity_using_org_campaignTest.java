package com.Com.EndToEndTest;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Com.GenericLib.BaseClass;
import com.Com.GenericLib.CommonUtils;
import com.crm.objectrepositoryLib.Campaign;
import com.crm.objectrepositoryLib.CreatingNewCampaign;
import com.crm.objectrepositoryLib.CreatingNewOpportunities;
import com.crm.objectrepositoryLib.CreatingNewOrganisation;
import com.crm.objectrepositoryLib.Home;
import com.crm.objectrepositoryLib.Opportunities;
import com.crm.objectrepositoryLib.OpportunityInformationPage;
import com.crm.objectrepositoryLib.Organisations;

public class CreateOpertunity_using_org_campaignTest extends BaseClass {
@Test (groups="smoketest",priority=6)
	public static void createOpertunity_using_org_campaignTest() throws Throwable {
		CommonUtils lib=new CommonUtils();
//		step:2 get data from excel sheet
        String orgName=lib.returnData("Sheet1",6,4)+ran.nextInt();;
		String opportunityName=lib.returnData("Sheet1",6,6)+ran.nextInt();;
		String campName=lib.returnData("Sheet1",6,8)+ran.nextInt();
		System.out.println("got data from excel successfully");	
//		step:3 navigate to campaign
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToMore();
		homepage.navigateToCampaign();
//		step:4 create new campaign
		Campaign campaignPage=PageFactory.initElements(driver, Campaign.class);
		campaignPage.navigateToCreateNewCampaign();;
		CreatingNewCampaign creatingCampaignpage=PageFactory.initElements(driver,CreatingNewCampaign.class);
		creatingCampaignpage.createCampaign(campName);
//		step:5 navigate to organisation
		homepage.navigateToOrganisation();
//		step:6 create new organisation
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
//      step:7 navigate to opportunity		
		homepage.navigateToOpportunity();
//      step:8 create opportunity
		Opportunities opportunitiesPage=PageFactory.initElements(driver, Opportunities.class);
		opportunitiesPage.navigateToCreateNewOpportunities();
		CreatingNewOpportunities creatingOpportinityPage=PageFactory.initElements(driver,CreatingNewOpportunities.class);
		creatingOpportinityPage.createOppWithOrgAndCamp(opportunityName,orgName, campName);
//		step:9 verification
//		step:10 navigate to opportunityInfo page
		OpportunityInformationPage oppInfoPage=PageFactory.initElements(driver, OpportunityInformationPage.class);
		String actualOppName=oppInfoPage.getOpportunityName().getText();
		String actualOrgName=oppInfoPage.getOrganizationName().getText();
        String actualCampaign=oppInfoPage.getCampaignName().getText();
		boolean status=(actualOrgName.equals(orgName)&&actualCampaign.trim().equals(campName))&&actualOppName.trim().equals(opportunityName);
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(status);
		Reporter.log("Opportuuty with org and campaign created==pass",true);
		}
}



