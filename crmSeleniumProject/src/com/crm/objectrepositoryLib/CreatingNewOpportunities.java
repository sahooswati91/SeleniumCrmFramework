package com.crm.objectrepositoryLib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Com.GenericLib.BaseClass;
import com.Com.GenericLib.WebdriverCommonLib;

public class CreatingNewOpportunities extends WebdriverCommonLib {
	@FindBy(name="potentialname") WebElement opportunityNameEdt;
	@FindBy(xpath="//input[@class='crmbutton small save']") WebElement newOpportunityPageSaveBtn;
	@FindBy(xpath="//input[@id='related_to_display']/following-sibling::img[@title='Select']")
	WebElement opp_plus_org;
	@FindBy(xpath="//input[@name='campaignid']/following-sibling::img[@title='Select']")
	WebElement opp_plus_campaign;
	@FindBy(id="search_txt") WebElement orgNameSerachEdt;
    @FindBy(name="search") WebElement searchBtn;
    public void enterOpportunityName(String opportunityname)
	{
		opportunityNameEdt.sendKeys(opportunityname);
	}
	
	public void clicknewOpportunitySaveBtn()
	{
		newOpportunityPageSaveBtn.click();
	}
	public void createOppWithOrgAndCamp(String oppName,String Organisationname,String campaignName) throws InterruptedException
	{
		opportunityNameEdt.sendKeys(oppName);
		opp_plus_org.click();
		switchTochildWindow();
		waitForElement(orgNameSerachEdt);
		orgNameSerachEdt.sendKeys(Organisationname);
		searchBtn.click();
		BaseClass.driver.findElement(By.linkText(Organisationname)).click();
		switchBackToParentWindow();
		opp_plus_campaign.click();
		switchTochildWindow();
		waitForElement(orgNameSerachEdt);
		orgNameSerachEdt.sendKeys(campaignName);
		searchBtn.click();
		BaseClass.driver.findElement(By.linkText(campaignName)).click();
		switchBackToParentWindow();
//		waitForElement(newOpportunityPageSaveBtn);
		Thread.sleep(4000);
		newOpportunityPageSaveBtn.click();
		}
	public void createOppWithOrgAndCamp(String oppName,String Organisationname) throws Throwable
	{
		opportunityNameEdt.sendKeys(oppName);
		opp_plus_org.click();
		switchTochildWindow();
		waitForElement(orgNameSerachEdt);
		orgNameSerachEdt.sendKeys(Organisationname);
		searchBtn.click();
		BaseClass.driver.findElement(By.linkText(Organisationname)).click();
		switchBackToParentWindow();
		Thread.sleep(4000);
		newOpportunityPageSaveBtn.click();

	}

}
