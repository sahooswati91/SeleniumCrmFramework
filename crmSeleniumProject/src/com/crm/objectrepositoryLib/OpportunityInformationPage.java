package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpportunityInformationPage {
	@FindBy(xpath="//a[@title='Organizations']")
	WebElement organizationName;
	public WebElement getOrganizationName() {
		return organizationName;
	}

	@FindBy(xpath="//td[text()='Campaign Source']/following-sibling::td[1]")
	WebElement campaignName;
	@FindBy(id="mouseArea_Opportunity Name") WebElement opportunityName;

	public WebElement getOpportunityName() {
		return opportunityName;
	}

	public WebElement getCampaignName() {
		return campaignName;
	}

	

}
