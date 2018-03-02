package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrganisationInformation {
	@FindBy(id="mouseArea_Organization Name") WebElement organizationName;
    @FindBy( id="mouseArea_Industry") WebElement industryForOrganization;
	public WebElement getIndustryForOrganization() {
		return industryForOrganization;
	}
	public WebElement getOrganizationName() {
		return organizationName;
	}
	

}
