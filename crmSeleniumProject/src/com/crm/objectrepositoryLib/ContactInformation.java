package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactInformation {
	@FindBy(id="mouseArea_Last Name") WebElement contactLastname;
	@FindBy(xpath="//td[@id='mouseArea_Organization Name']/a")
	WebElement organisationWithContact;
	@FindBy(xpath="//img[@title='Contact Image']") WebElement contactImage;
	public WebElement getContactImage() {
		return contactImage;
	}
	public WebElement getOrganisationWithContact() {
		return organisationWithContact;
	}
	public WebElement getLastnameContactInfo()
	{
		return contactLastname;
	}

}
