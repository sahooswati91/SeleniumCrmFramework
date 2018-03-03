package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.GenericLib.CommonUtils;
import com.crm.GenericLib.WebdriverCommonLib;

public class Organisations extends WebdriverCommonLib {
	@FindBy(xpath="//img[@title='Create Organization...']") 
	WebElement createOrganisonImage;
	
	@FindBy(name="search_text") 
	WebElement orgNameSearchEdtParentOrg;
    
	@FindBy(xpath="//input[@class='crmbutton small create'and@name='submit']") 
	WebElement searchBtn;
	
	@FindBy(id="bas_searchfield") WebElement SearchType;
	
	@FindBy(xpath="(//a[@title='Organizations']/../../td[1]/input)[1]") 
	WebElement orgCheckBox;
	
	@FindBy(xpath="//input[@class='crmbutton small delete']") 
	WebElement deleteBtn;
	
	
	public void searchOrganisationName(String orgnisationName) throws Throwable
	{
		System.out.println(orgnisationName);
		selectFromDropDown(SearchType, "Organization Name");
//		waitForElement(orgNameSerachEdt);
		Thread.sleep(7000);
		orgNameSearchEdtParentOrg.sendKeys(orgnisationName);
		searchBtn.click();
		Thread.sleep(3000);
		
	}
	public void navigateToCreateNewOrganiZation()
	{
		waitForElement(createOrganisonImage);
		createOrganisonImage.click();	
	}
	public void deleteOrganisation() throws InterruptedException
	{
//		waitForElement(orgCheckBox);
		Thread.sleep(2000);
		orgCheckBox.click();
		Thread.sleep(2000);
		deleteBtn.click();
		handleAlert();
		System.out.println("organisation deleted");
	}

}
