package com.crm.EndToEndTest;


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

import com.crm.GenericLib.BaseClass;
import com.crm.GenericLib.CommonUtils;
import com.crm.objectrepositoryLib.Campaign;
import com.crm.objectrepositoryLib.ContactInformation;
import com.crm.objectrepositoryLib.Contacts;
import com.crm.objectrepositoryLib.CreatingNewCampaign;
import com.crm.objectrepositoryLib.CreatingNewContacts;
import com.crm.objectrepositoryLib.CreatingNewOpportunities;
import com.crm.objectrepositoryLib.CreatingNewOrganisation;
import com.crm.objectrepositoryLib.Home;
import com.crm.objectrepositoryLib.Opportunities;
import com.crm.objectrepositoryLib.OpportunityInformationPage;
import com.crm.objectrepositoryLib.Organisations;

public class CreateOpertunity_using_org_campaignTest extends BaseClass {
@Test (groups="smoketest",priority=6)
	public static void createOpertunity_using_org_campaignTest() throws Throwable {
	Reporter.log("=========createOpertunity_using_org_campaignTest=====",true);
	CommonUtils lib=new CommonUtils();
//		step:2 get data from excel sheet
		Reporter.log("step:2 get data from excel sheet",true);
        String orgName=lib.returnData("Sheet1",6,4)+ran.nextInt();;
		String opportunityName=lib.returnData("Sheet1",6,6)+ran.nextInt();;
		String campName=lib.returnData("Sheet1",6,8)+ran.nextInt();
		Reporter.log("got data from excel successfully",true);	
//		step:3 navigate to campaign
		Reporter.log("step:3 navigate to campaign",true);
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToMore();
		homepage.navigateToCampaign();
//		step:4 create new campaign
		Reporter.log("step:4 create new campaign",true);
		Campaign campaignPage=PageFactory.initElements(driver, Campaign.class);
		campaignPage.navigateToCreateNewCampaign();;
		CreatingNewCampaign creatingCampaignpage=PageFactory.initElements(driver,CreatingNewCampaign.class);
		creatingCampaignpage.createCampaign(campName);
//		step:5 navigate to organisation
		Reporter.log("step:5 navigate to organisation",true);
		homepage.navigateToOrganisation();
//		step:6 create new organisation
		Reporter.log("step:6 create new organisation",true);
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
//      step:7 navigate to opportunity
		Reporter.log("step:7 navigate to opportunity",true);
		homepage.navigateToOpportunity();
//      step:8 create opportunity
		Reporter.log("step:8 create opportunity",true);
		Opportunities opportunitiesPage=PageFactory.initElements(driver, Opportunities.class);
		opportunitiesPage.navigateToCreateNewOpportunities();
		CreatingNewOpportunities creatingOpportinityPage=PageFactory.initElements(driver,CreatingNewOpportunities.class);
		creatingOpportinityPage.createOppWithOrgAndCamp(opportunityName,orgName, campName);
//		step:9 verification
		Reporter.log("step:9 verification",true);
//		step:10 navigate to opportunityInfo page
		Reporter.log("step:10 navigate to opportunityInfo page",true);
		OpportunityInformationPage oppInfoPage=PageFactory.initElements(driver, OpportunityInformationPage.class);
		String actualOppName=oppInfoPage.getOpportunityName().getText();
		String actualOrgName=oppInfoPage.getOrganizationName().getText();
        String actualCampaign=oppInfoPage.getCampaignName().getText();
		boolean status=(actualOrgName.equals(orgName)&&actualCampaign.trim().equals(campName))&&actualOppName.trim().equals(opportunityName);
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(status);
		Reporter.log("Opportuuty with org and campaign created==pass",true);
		}
 @Test (groups="regressiontest",priority=7)
 public static void DeleteOrgWhenOrgContainsContact_OppTest() throws Throwable {
	 Reporter.log("=========Delete_Org_WhenOrgContains_Contact_OppTest=========",true);
	 CommonUtils lib=new CommonUtils();
//	Step:2 get data from excel file
		Reporter.log("Step:2 get data from excel file",true);
		String orgName=lib.returnData("Sheet1",7,4)+ran.nextInt();
		String lastName=lib.returnData("Sheet1",7,2);
		String opportunityName=lib.returnData("Sheet1",7,6);
//	Step:3 navigate to organisation
		Reporter.log("Step:3 navigate to organisation",true);
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToOrganisation();
//	Step:4 create new organisation
		Reporter.log("Step:4 create new organisation",true);
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
		Reporter.log("organisation created successfullly",true);
//	Step:5 navigate to contact
		Reporter.log("Step:5 navigate to contact",true);
	       homepage.navigateToContact();
//	Step:6 create new contact
	       Reporter.log("Step:6 create new contact",true);
	    Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
	        contactsPage.navigateToCreateNewContacts();
	        CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
	        creatingNewContactspage.createContact(lastName, orgName);
	        Reporter.log("contact with organisation created successfully",true);		
			//navigate to opportunity
	        Reporter.log("//navigate to opportunity",true);
			homepage.navigateToOpportunity();
//Step:7 create opportunity
			Reporter.log("//Step:7 create opportunity",true);
			Opportunities opportunitiesPage=PageFactory.initElements(driver, Opportunities.class);
			opportunitiesPage.navigateToCreateNewOpportunities();
//Step:8	go to organisation window from create opportunity window
			Reporter.log("//Step:8 go to organisation window from create opportunity window",true);
			CreatingNewOpportunities createoppPage=PageFactory.initElements(driver, CreatingNewOpportunities.class);
			createoppPage.createOppWithOrgAndCamp(opportunityName, orgName);
			Reporter.log("Opportunity with organisation saved successfully",true);
//Step:9	navigate to organisation again
			Reporter.log("//Step:9 navigate to organisation again",true);
			homepage.navigateToOrganisation();
//Step:10	search organisation name
			Reporter.log("//Step:10 search organisation name",true);
			Reporter.log(orgName);
			OrganisationsPage.searchOrganisationName(orgName);
//Step:11 click on organisation check box and delete
			Reporter.log("//Step:11 click on organisation check box and delete",true);
			OrganisationsPage.deleteOrganisation();
//Step:12 verify contact for that organisation exists
			Reporter.log("//Step:12 verify contact for that organisation exists",true);
//Step:13 navigate to contact
			Reporter.log("//Step:13 navigate to contact",true);
			Thread.sleep(5000);
			homepage.navigateToContact();
			
//Step:14	select lastname for search
			Reporter.log("//Step:14 select lastname for search",true);
			contactsPage.selectFromContactType("Last Name");
//Step:15  type contact name in searchbox and search
			Reporter.log("//Step:15 type contact name in searchbox and search",true);
			Thread.sleep(6000);
			contactsPage.searchContactName(lastName);
//step:16 verificaton for organisation from contact
			Reporter.log("//step:16 verificaton for organisation from contact",true);
			ContactInformation cinfoPage=PageFactory.initElements(driver, ContactInformation.class);
			String actualContact=cinfoPage.getLastnameContactInfo().getText();
			String actualOrgname=cinfoPage.getOrganisationWithContact().getText();
			Reporter.log("actual org name="+actualOrgname);
			Boolean statusForOrgName=actualOrgname.isEmpty();
			SoftAssert a3=new SoftAssert();
			a3.assertEquals(actualContact.trim(), lastName.trim());
			Reporter.log("Contact still exists==pass",true);
			a3.assertTrue(statusForOrgName);
			Reporter.log("Organisation got deleted==pass",true);
			a3.assertAll();
//step:17 navigate to opportunity
			Reporter.log("//step:17 navigate to opportunity",true);
			homepage.navigateToOpportunity();
//step:18 click on opportunity name from dropdown
			Reporter.log("//step:18 click on opportunity name from dropdown",true);
			opportunitiesPage.selectFromOrgType("Opportunity Name");
//step:19 type opportunityname in search
			Reporter.log("//step:19 type opportunityname in search",true);
			opportunitiesPage.searchOppName(opportunityName);
//step:20  verification for opportunity
			Reporter.log("//step:20  verification for opportunity",true);
			a3.assertTrue(opportunitiesPage.getNoOppFoundMsg().isDisplayed());
			Reporter.log("Opportunity got deleted==pass",true);
		
	}
}



