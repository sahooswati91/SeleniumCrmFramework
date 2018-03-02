package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatingNewCampaign {
	@FindBy(xpath="//input[@name='campaignname']") WebElement campaignNameEdt;
	@FindBy(xpath="//input[@class='crmButton small save']") WebElement campaignNameSaveBtn;
	public void enterCampaignName(String campaignname)
	{
		campaignNameEdt.sendKeys(campaignname);
	}
	
	public void clicknewCampaignSaveBtn()
	{
		campaignNameSaveBtn.click();
	}
	public void createCampaign(String campaignname)
	{
		campaignNameEdt.sendKeys(campaignname);
		campaignNameSaveBtn.click();
		
	}

}
