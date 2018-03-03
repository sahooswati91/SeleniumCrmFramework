package com.crm.objectrepositoryLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.GenericLib.BaseClass;
import com.crm.GenericLib.WebdriverCommonLib;

public class Opportunities extends WebdriverCommonLib {
	@FindBy(xpath="//img[@title='Create Opportunity...']") 
	WebElement createOpportunitiesImage;
	
	@FindBy(id="bas_searchfield")
	WebElement oppSearchDropDown;
	
	@FindBy(name="search_text")
	WebElement oppSearchEdt;
	
    @FindBy(xpath="//input[@class='crmbutton small create'and@name='submit']")
    WebElement searchOppBtn;
    
    @FindBy(xpath="//span[@class='genHeaderSmall']")
	WebElement noOppFoundMsg;
	
	public WebElement getNoOppFoundMsg() {
		return noOppFoundMsg;
	}
	
	public void navigateToCreateNewOpportunities()
	{
		waitForElement(createOpportunitiesImage);
		createOpportunitiesImage.click();	
	}
	public void selectFromOrgType(String orgType)
	{
		waitForElement(oppSearchDropDown);
		selectFromDropDown(oppSearchDropDown,orgType);
	}
	public void searchOppName(String opportunityName) throws InterruptedException
	{
		waitForElement(oppSearchEdt);
		oppSearchEdt.sendKeys(opportunityName);
		waitForElement(searchOppBtn);
		searchOppBtn.click();
		Thread.sleep(2000);
	}
	

}
