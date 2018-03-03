package com.crm.objectrepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Com.GenericLib.WebdriverCommonLib;

public class Home extends WebdriverCommonLib{
	@FindBy(xpath="//a[text()='Contacts']") WebElement contactLink;
	@FindBy(xpath="//a[text()='Organizations']") WebElement organizationsLink;
	@FindBy(xpath="//img[@src='themes/softed/images/menuDnArrow.gif']") WebElement MoreLink;
	 @FindBy(xpath="//a[text()='Campaigns']") WebElement campaignLink;
	 @FindBy(xpath="//a[text()='Opportunities']") WebElement opportunityLink;
	public void navigateToContact()
	 {
		 waitForElement(contactLink);
		 contactLink.click();
//		 handleAlert();
		 
	 }
	 public void navigateToOrganisation()
	 {
		 waitForElement(organizationsLink);
		 organizationsLink.click(); 
		 
	 }
	 public void navigateToMore()
	 {
		 waitForElement(MoreLink);
		 MoreLink.click(); 
		 
	 }
	 public void navigateToCampaign()
	 {
		 waitForElement(campaignLink);
		 campaignLink.click(); 
		 
	 }
	 public void navigateToOpportunity()
	 {
		 waitForElement(opportunityLink);
		 opportunityLink.click(); 
		 
	 }
	 
	 
	

}
