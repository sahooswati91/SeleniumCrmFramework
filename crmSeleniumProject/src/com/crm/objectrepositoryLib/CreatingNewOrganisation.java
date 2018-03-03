package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.GenericLib.WebdriverCommonLib;

public class CreatingNewOrganisation extends WebdriverCommonLib {
	@FindBy(name="accountname") WebElement organizationNameEdit;
	@FindBy(xpath="//input[@class='crmbutton small save']") WebElement newOrganizationSaveBtn;
	@FindBy(name="industry") WebElement industryDropDown;
	public void enterOrganizationName(String orgname)
	{
		organizationNameEdit.sendKeys(orgname);
	}
	public void createOrganization(String oranisationName)
	{
		organizationNameEdit.sendKeys(oranisationName);
		newOrganizationSaveBtn.click();
		
	}
	public void createOrganization(String oranisationName,String industry)
	{
		organizationNameEdit.sendKeys(oranisationName);
		waitForElement(industryDropDown);
		selectFromDropDown(industryDropDown,industry);
		waitForElement(newOrganizationSaveBtn);
		newOrganizationSaveBtn.click();
		
	}
	

}
