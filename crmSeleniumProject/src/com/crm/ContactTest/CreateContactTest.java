package com.crm.ContactTest;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLib.BaseClass;
import com.crm.GenericLib.CommonUtils;
import com.crm.objectrepositoryLib.ContactInformation;
import com.crm.objectrepositoryLib.Contacts;
import com.crm.objectrepositoryLib.CreatingNewContacts;
import com.crm.objectrepositoryLib.Home;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@Listeners (com.crm.GenericLib.SampleListener.class)
public class CreateContactTest extends BaseClass {
	@Test (groups="smoketest",priority=1)
        public void createContact() throws Throwable{
		ExtentTest log=extent.startTest("createContact");
//		Reporter.log("=======Create Contact Test========",true);
		log.log(LogStatus.INFO, "log in to app by extent report");
		  CommonUtils lib=new CommonUtils();
//		step:2  get data from excel sheet
//		Reporter.log("step:2  get data from excel sheet",true);
		  log.log(LogStatus.INFO, "step:2  get data from excel sheet by extent report");
		String lastName=lib.returnData("Sheet1",1,2);
		Reporter.log(lastName,true);
//		step:3 navigate to contact
//		step
		Reporter.log("step:3 navigate to contact",true);
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToContact();
//		step:4 create new contact
		Reporter.log("step:4 create new contact",true);
		Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
		contactsPage.navigateToCreateNewContacts();
		CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
		creatingNewContactspage.createContact(lastName);
//		step:5 verification
		Reporter.log("step:5 verification",true);
//		step:6 navigate to contact information page
		Reporter.log("step:6 navigate to contact information page",true);
		ContactInformation cinfoPage=PageFactory.initElements(driver, ContactInformation.class);
		String expectedContact=cinfoPage.getLastnameContactInfo().getText();
		SoftAssert a1=new SoftAssert();
		a1.assertEquals(lastName.trim(),expectedContact.trim());
		log.log(LogStatus.PASS, "Contact created successfully==pass");
//		Reporter.log("Contact created successfully==pass",true);
		a1.assertAll();
		extent.endTest(log);
	}
	@Test (groups="regressiontest",priority=2)
public static void createContactwithimageTest() throws Throwable {
		Reporter.log("=======create Contact with imageTest========",true);
		CommonUtils lib=new CommonUtils();
//      step:2 get data from excel file
		Reporter.log("step:2 get data from excel file",true);
		String lastName=lib.returnData("Sheet1",2,2);
		String imgPath=lib.returnData("Sheet1",2,9);
//		step:3 navigate to contact
		Reporter.log("step:3 navigate to contact",true);
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToContact();
//		step:4 create new contact
		Reporter.log("step:4 create new contact",true);
		Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
		contactsPage.navigateToCreateNewContacts();
		CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
//		step:5 create contact with image
		Reporter.log("step:5 create contact with image",true);
		creatingNewContactspage.createContactWithImage(lastName, imgPath);
//		step:6 verification
//		step:7 navigate to contact information page
		Reporter.log("step:7 navigate to contact information page",true);
		ContactInformation contactInformationPage=PageFactory.initElements(driver,ContactInformation.class );
		String actualLastname=contactInformationPage.getLastnameContactInfo().getText();
		SoftAssert a2=new SoftAssert();
		a2.assertEquals(actualLastname.trim(),lastName.trim());
		Reporter.log("Contact created successfully==pass",true);
		boolean status=contactInformationPage.getContactImage().isDisplayed();
		a2.assertTrue(status);
		Reporter.log("Contact created with image==pass",true);
		a2.assertAll();
				
	}
	@Test (groups="regressiontest",priority=3)
	public static void createContactWithOrganisationTest() throws Throwable {
		// TODO Auto-generated method stub
		Reporter.log("=======create Contact With Organisation Test========",true);
		 CommonUtils lib=new CommonUtils();
//		step:2 get data from excel file
		Reporter.log("step:2 get data from excel file",true);
		String lastName=lib.returnData("Sheet1",3,2);
		String orgName=lib.returnData("Sheet1",3,4);
//      step:3 create organisation
		Reporter.log("step:3 create organisation");
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		Reporter.log("organisation created successfullly",true);
//		step:4 navigate to contact
		Reporter.log("step:4 navigate to contact");
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToContact();
//		step:5 create new contact
		Reporter.log("step:5 create new contact",true);
		Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
		contactsPage.navigateToCreateNewContacts();
		CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
		creatingNewContactspage.createContact(lastName, orgName);
//		step:6 verification
		Reporter.log("step:6 verification",true);
//		step:7 navigate to contact information page
		Reporter.log("step:7 navigate to contact information page",true);
		ContactInformation cinfoPage=PageFactory.initElements(driver, ContactInformation.class);
		String actualContact=cinfoPage.getLastnameContactInfo().getText();
		String actualOrgname=cinfoPage.getOrganisationWithContact().getText();
		SoftAssert a3=new SoftAssert();
		a3.assertEquals(actualContact.trim(), lastName.trim());
		Reporter.log("Contact created successfully==pass",true);
		a3.assertEquals(actualOrgname, orgName);
		Reporter.log("Organisation created successfully==pass",true);
		a3.assertAll();
	}

}
