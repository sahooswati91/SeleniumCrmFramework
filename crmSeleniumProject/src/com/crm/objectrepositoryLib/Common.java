package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.GenericLib.WebdriverCommonLib;

public class Common extends WebdriverCommonLib {
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") WebElement administrator;
	@FindBy(xpath="//a[text()='Sign Out']") WebElement singOutClickLink;
	public void logOut() throws Throwable
	{
		mouseMove(administrator);
		singOutClickLink.click();
	}
	

}
