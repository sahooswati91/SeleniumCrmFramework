package com.crm.objectrepositoryLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.GenericLib.BaseClass;
import com.crm.GenericLib.WebdriverCommonLib;

public class Contacts extends WebdriverCommonLib {
	@FindBy(xpath="//img[@title='Create Contact...']") 
	WebElement createContactsImage;
	
	@FindBy(id="bas_searchfield")
	WebElement searchContactDropDown;
	 
	@FindBy(name="search_text")
	WebElement  searchContactEdt;
	
	@FindBy(xpath="//input[@class='crmbutton small create'and@name='submit']")
	WebElement searchContactBtn;
	
	public void navigateToCreateNewContacts()
	{
		waitForElement(createContactsImage);
		createContactsImage.click();	
	}
	
	public void selectFromContactType(String contactType)
	{
		waitForElement(searchContactDropDown);
		selectFromDropDown(searchContactDropDown, contactType);
	}
	public void searchContactName(String lastNameContact) throws InterruptedException
	{
		waitForElement(searchContactEdt);
		searchContactEdt.sendKeys(lastNameContact);
		waitForElement(searchContactBtn);
		searchContactBtn.click();
		Thread.sleep(2000);
		BaseClass.driver.findElement(By.linkText(lastNameContact)).click();
	}
	
	
	

}
