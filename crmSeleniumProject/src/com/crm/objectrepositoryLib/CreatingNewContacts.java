package com.crm.objectrepositoryLib;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Com.GenericLib.BaseClass;
import com.Com.GenericLib.WebdriverCommonLib;

public class CreatingNewContacts extends WebdriverCommonLib {
	@FindBy(name="lastname") WebElement contactLastNameEdit;
	@FindBy(xpath="//input[@class='crmButton small save']") WebElement newContactPageSaveBtn;
    @FindBy(id="search_txt") WebElement orgNAmeSerachEdt;
    @FindBy(name="search") WebElement searchBtn;
    @FindBy(xpath="//input[@name='account_id']/following-sibling::img[1]")
	WebElement org_Plus_image;
    @FindBy(name="imagename") WebElement imageForContact;
	public void enterContactLastName(String lstname)
	{
		contactLastNameEdit.sendKeys(lstname);
	}
	
	public void clicknewContactPageSaveBtn()
	{
		newContactPageSaveBtn.click();
	}
	public void createContact(String lstname)
	{
		contactLastNameEdit.sendKeys(lstname);
		newContactPageSaveBtn.click();
		
	}
	public void createContact(String lstname,String orgnisationName)
	{
		contactLastNameEdit.sendKeys(lstname);
		org_Plus_image.click();
		switchTochildWindow();
		waitForElement(orgNAmeSerachEdt);
		orgNAmeSerachEdt.sendKeys(orgnisationName);
		searchBtn.click();
		BaseClass.driver.findElement(By.linkText(orgnisationName)).click();
		switchBackToParentWindow();
		newContactPageSaveBtn.click();
		
		}
	public void createContactWithImage(String lstname,String imagePath) throws InterruptedException, AWTException
	{
		contactLastNameEdit.sendKeys(lstname);
		imageForContact.click();
		fileUploading(imagePath);
//		waitForElement(newContactPageSaveBtn);
		newContactPageSaveBtn.click();
		
		
	}
	
	

}
