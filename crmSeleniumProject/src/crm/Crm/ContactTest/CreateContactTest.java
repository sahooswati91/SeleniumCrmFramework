package crm.Crm.ContactTest;


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

import com.Com.GenericLib.BaseClass;
import com.Com.GenericLib.CommonUtils;
import com.crm.objectrepositoryLib.ContactInformation;
import com.crm.objectrepositoryLib.Contacts;
import com.crm.objectrepositoryLib.CreatingNewContacts;
import com.crm.objectrepositoryLib.Home;
@Listeners (com.Com.GenericLib.SampleListener.class)
public class CreateContactTest extends BaseClass {
	@Test (groups="smoketest",priority=1)
        public void createContact() throws Throwable{
		  CommonUtils lib=new CommonUtils();
//		step:2  get data from excel sheet
		String lastName=lib.returnData("Sheet1",1,2);
		System.out.println(lastName);
//		step:3 navigate to contact
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToContact();
//		step:4 create new contact
		Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
		contactsPage.navigateToCreateNewContacts();
		CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
		creatingNewContactspage.createContact(lastName);
//		step:5 verification
//		step:6 navigate to contact information page
		ContactInformation cinfoPage=PageFactory.initElements(driver, ContactInformation.class);
		String expectedContact=cinfoPage.getLastnameContactInfo().getText();
		SoftAssert a1=new SoftAssert();
		a1.assertEquals(lastName.trim(),expectedContact.trim());
		Reporter.log("Contact created successfully==pass",true);
		a1.assertAll();
	}
	@Test (groups="regressiontest",priority=2)
public static void createContactwithimageTest() throws Throwable {
		CommonUtils lib=new CommonUtils();
//      step:2 get data from excel file
		String lastName=lib.returnData("Sheet1",2,2);
		String imgPath=lib.returnData("Sheet1",2,9);
//		step:3 navigate to contact
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToContact();
//		step:4 create new contact
		Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
		contactsPage.navigateToCreateNewContacts();
		CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
//		step:5 create contact with image
		creatingNewContactspage.createContactWithImage(lastName, imgPath);
//		step:6 verification
//		step:7 navigate to contact information page
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
		 CommonUtils lib=new CommonUtils();
//		step:2 get data from excel file
		String lastName=lib.returnData("Sheet1",3,2);
		String orgName=lib.returnData("Sheet1",3,4);
//      step:3 create organisation
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		System.out.println("organisation created successfullly");
//		step:4 navigate to contact
		Home homepage=PageFactory.initElements(driver, Home.class);
		homepage.navigateToContact();
//		step:5 create new contact
		Contacts contactsPage=PageFactory.initElements(driver, Contacts.class);
		contactsPage.navigateToCreateNewContacts();
		CreatingNewContacts creatingNewContactspage=PageFactory.initElements(driver,CreatingNewContacts.class);
		creatingNewContactspage.createContact(lastName, orgName);
//		step:6 verification
//		step:7 navigate to contact information page
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