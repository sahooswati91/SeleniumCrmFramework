package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.GenericLib.WebdriverCommonLib;

public class Campaign extends WebdriverCommonLib {
	@FindBy(xpath="//img[@title='Create Campaign...']") WebElement createCampaignImage;
	public void navigateToCreateNewCampaign()
	{
		waitForElement(createCampaignImage);
		createCampaignImage.click();	
	}

}
