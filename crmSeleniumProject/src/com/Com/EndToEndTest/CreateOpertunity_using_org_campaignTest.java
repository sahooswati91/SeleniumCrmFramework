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
	System.out.println("=========createOpertunity_using_org_campaignTest=====");
	CommonUtils lib=new CommonUtils();
//		step:2 get data from excel sheet
		System.out.println("step:2 get data from excel sheet");
        String orgName=lib.returnData("Sheet1",6,4)+ran.nextInt();;
		String opportunityName=lib.returnData("Sheet1",6,6)+ran.nextInt();;
		String campName=lib.returnData("Sheet1",6,8)+ran.nextInt();
		System.out.println("got data from excel successfully");	
//		step:3 navigate to campaign
		System.out.println("step:3 navigate to campaign");
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToMore();
		homepage.navigateToCampaign();
//		step:4 create new campaign
		System.out.println("step:4 create new campaign");
		Campaign campaignPage=PageFactory.initElements(driver, Campaign.class);
		campaignPage.navigateToCreateNewCampaign();;
		CreatingNewCampaign creatingCampaignpage=PageFactory.initElements(driver,CreatingNewCampaign.class);
		creatingCampaignpage.createCampaign(campName);
//		step:5 navigate to organisation
		System.out.println("step:5 navigate to organisation");
		homepage.navigateToOrganisation();
//		step:6 create new organisation
		System.out.println("step:6 create new organisation");
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
//      step:7 navigate to opportunity
		System.out.println("step:7 navigate to opportunity");
		homepage.navigateToOpportunity();
//      step:8 create opportunity
		System.out.println("step:8 create opportunity");
		Opportunities opportunitiesPage=PageFactory.initElements(driver, Opportunities.class);
		opportunitiesPage.navigateToCreateNewOpportunities();
		CreatingNewOpportunities creatingOpportinityPage=PageFactory.initElements(driver,CreatingNewOpportunities.class);
		creatingOpportinityPage.createOppWithOrgAndCamp(opportunityName,orgName, campName);
//		step:9 verification
		System.out.println("step:9 verification");
//		step:10 navigate to opportunityInfo page
		System.out.println("step:10 navigate to opportunityInfo page");
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
	 System.out.println("=========Delete_Org_WhenOrgContains_Contact_OppTest=========");
	 CommonUtils lib=new CommonUtils();
//	Step:2 get data from excel file
		System.out.println("Step:2 get data from excel file");
		String orgName=lib.returnData("Sheet1",7,4)+ran.nextInt();
		String lastName=lib.returnData("Sheet1",7,2);
		String opportunityName=lib.returnData("Sheet1",7,6);
//	Step:3 navigate to organisation
		System.out.println("Step:3 navigate to organisation");
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToOrganisation();
//	Step:4 create new organisation
		System.out.println("Step:4 create new organisation");
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
		System.out.println("organisation created successfullly");
//	Step:5 navigate to contact
		System.out.println("Step:5 navigate to contact");
	       homepage.navigateToContact();
//	Step:6 create new contact
	       System.out.println("Step:6 create new contact");
	    Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
	        contactsPage.navigateToCreateNewContacts();
	        CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
	        creatingNewContactspage.createContact(lastName, orgName);
	        System.out.println("contact with organisation created successfully");		
			//navigate to opportunity
	        System.out.println("//navigate to opportunity");
			homepage.navigateToOpportunity();
//Step:7 create opportunity
			System.out.println("//Step:7 create opportunity");
			Opportunities opportunitiesPage=PageFactory.initElements(driver, Opportunities.class);
			opportunitiesPage.navigateToCreateNewOpportunities();
//Step:8	go to organisation window from create opportunity window
			System.out.println("//Step:8 go to organisation window from create opportunity window");
			CreatingNewOpportunities createoppPage=PageFactory.initElements(driver, CreatingNewOpportunities.class);
			createoppPage.createOppWithOrgAndCamp(opportunityName, orgName);
			System.out.println("Opportunity with organisation saved successfully");
//Step:9	navigate to organisation again
			System.out.println("//Step:9 navigate to organisation again");
			homepage.navigateToOrganisation();
//Step:10	search organisation name
			System.out.println("//Step:10 search organisation name");
			System.out.println(orgName);
			OrganisationsPage.searchOrganisationName(orgName);
//Step:11 click on organisation check box and delete
			System.out.println("//Step:11 click on organisation check box and delete");
			OrganisationsPage.deleteOrganisation();
//Step:12 verify contact for that organisation exists
			System.out.println("//Step:12 verify contact for that organisation exists");
//Step:13 navigate to contact
			System.out.println("//Step:13 navigate to contact");
			Thread.sleep(5000);
			homepage.navigateToContact();
			
//Step:14	select lastname for search
			System.out.println("//Step:14 select lastname for search");
			contactsPage.selectFromContactType("Last Name");
//Step:15  type contact name in searchbox and search
			System.out.println("//Step:15 type contact name in searchbox and search");
			Thread.sleep(6000);
			contactsPage.searchContactName(lastName);
//step:16 verificaton for organisation from contact
			System.out.println("//step:16 verificaton for organisation from contact");
			ContactInformation cinfoPage=PageFactory.initElements(driver, ContactInformation.class);
			String actualContact=cinfoPage.getLastnameContactInfo().getText();
			String actualOrgname=cinfoPage.getOrganisationWithContact().getText();
			System.out.println("actual org name="+actualOrgname);
			Boolean statusForOrgName=actualOrgname.isEmpty();
			SoftAssert a3=new SoftAssert();
			a3.assertEquals(actualContact.trim(), lastName.trim());
			Reporter.log("Contact still exists==pass",true);
			a3.assertTrue(statusForOrgName);
			Reporter.log("Organisation got deleted==pass",true);
			a3.assertAll();
//step:17 navigate to opportunity
			System.out.println("//step:17 navigate to opportunity");
			homepage.navigateToOpportunity();
//step:18 click on opportunity name from dropdown
			System.out.println("//step:18 click on opportunity name from dropdown");
			opportunitiesPage.selectFromOrgType("Opportunity Name");;
//step:19 type opportunityname in search
			System.out.println("//step:19 type opportunityname in search");
			opportunitiesPage.searchOppName(opportunityName);
//step:20  verification for opportunity
			System.out.println("//step:20  verification for opportunity");
			a3.assertTrue(opportunitiesPage.getNoOppFoundMsg().isDisplayed());
			Reporter.log("Opportunity got deleted==pass",true);
		
	}
}



