package com.Com.EndToEndTest;


import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Com.GenericLib.BaseClass;
import com.Com.GenericLib.CommonUtils;
import com.Com.GenericLib.WebdriverCommonLib;
import com.crm.objectrepositoryLib.ContactInformation;
import com.crm.objectrepositoryLib.Contacts;
import com.crm.objectrepositoryLib.CreatingNewContacts;
import com.crm.objectrepositoryLib.CreatingNewOpportunities;
import com.crm.objectrepositoryLib.CreatingNewOrganisation;
import com.crm.objectrepositoryLib.Home;
import com.crm.objectrepositoryLib.Opportunities;
import com.crm.objectrepositoryLib.Organisations;

public class DeleteOrgWhenOrgContainsContactOppTest extends BaseClass{
@Test (priority=7)
	public static void DeleteOrgWhenOrgContainsContact_OppTest() throws Throwable {
		CommonUtils lib=new CommonUtils();
//	Step:2 get data from excel file
		String orgName=lib.returnData("Sheet1",7,4)+ran.nextInt();
		String lastName=lib.returnData("Sheet1",7,2);
		String opportunityName=lib.returnData("Sheet1",7,6);
//	Step:3 navigate to organisation
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToOrganisation();
//	Step:4 create new organisation
		Organisations OrganisationsPage=PageFactory.initElements(driver, Organisations.class);
		OrganisationsPage.navigateToCreateNewOrganiZation();
		CreatingNewOrganisation creatingNewOrganisationpage=PageFactory.initElements(driver,CreatingNewOrganisation.class);
		creatingNewOrganisationpage.createOrganization((orgName));
		System.out.println("organisation created successfullly");
//	Step:5 navigate to contact
	       homepage.navigateToContact();
//	Step:6 create new contact
	    Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
	        contactsPage.navigateToCreateNewContacts();
	        CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
	        creatingNewContactspage.createContact(lastName, orgName);
	        System.out.println("contact with organisation created successfully");		
			//navigate to opportunity		
			homepage.navigateToOpportunity();
//Step:7 create opportunity
			Opportunities opportunitiesPage=PageFactory.initElements(driver, Opportunities.class);
			opportunitiesPage.navigateToCreateNewOpportunities();
//	Step:8	go to organisation window from create opportunity window
			CreatingNewOpportunities createoppPage=PageFactory.initElements(driver, CreatingNewOpportunities.class);
			createoppPage.createOppWithOrgAndCamp(opportunityName, orgName);
			System.out.println("Opportunity with organisation saved successfully");
//Step:9	navigate to organisation again
			homepage.navigateToOrganisation();
//Step:10	search organisation name
			System.out.println(orgName);
			OrganisationsPage.searchOrganisationName(orgName);
//Step:11	click on organisation check box and delete
			OrganisationsPage.deleteOrganisation();
//Step:12	verify contact for that organisation exists
//Step:13	navigate to contact
			homepage.navigateToContact();
			
//Step:14	select lastname for search
			contactsPage.selectFromContactType("Last Name");
//Step:15  type contact name in searchbox and search
			Thread.sleep(1000);
			contactsPage.searchContactName(lastName);
//step:16 verificaton for organisation from contact
			ContactInformation cinfoPage=PageFactory.initElements(driver, ContactInformation.class);
			String actualContact=cinfoPage.getLastnameContactInfo().getText();
			String actualOrgname=cinfoPage.getOrganisationWithContact().getText();
			System.out.println("actual org name="+actualOrgname);
			SoftAssert a3=new SoftAssert();
			a3.assertEquals(actualContact.trim(), lastName.trim());
			Reporter.log("Contact still exists==pass",true);
//			a3.assertNull(cinfoPage.getOrganisationWithContact());;
//			Reporter.log("Organisation is deleted==pass",true);
			a3.assertAll();
 //step:17 navigate to opportunity
			homepage.navigateToOpportunity();
//step:18 click on opportunity name from dropdown
			opportunitiesPage.selectFromOrgType("Opportunity Name");;
//step:19 type opportunityname in search
			opportunitiesPage.searchOppName(opportunityName);
		
	}

}
