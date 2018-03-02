package com.Com.GenericLib;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebdriverCommonLib {
	public void waitForElement(WebElement element){
		int wait = 0;
		while(wait<40){
			try{
				element.click();
				break;
			}catch(Throwable t){
				                     try {
					Thread.sleep(1000);
					wait++;
				                  } catch (InterruptedException e) {}
			}
		}
	}
	public void waitForPageLoad(){
		BaseClass.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void mouseMove(WebElement element) throws Throwable{
		Actions act = new Actions(BaseClass.driver);
		Thread.sleep(4000);
		act.moveToElement(element).perform();
	}
	
	public void handleAlert(){
		Alert alt = BaseClass.driver.switchTo().alert();
		alt.accept();
	}
	public static  String parentID="";
	public void switchTochildWindow(){
		Set<String> set = BaseClass.driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		 parentID = it.next();
		String childID = it.next();
		BaseClass.driver.switchTo().window(childID);
	}
	
	public void switchBackToParentWindow(){
		BaseClass.driver.switchTo().window(parentID);
	}
	public void fileUploading(String filePath) throws InterruptedException, AWTException
	{
		StringSelection path=new StringSelection(filePath);
		//get the control of the desktop tools
		Toolkit tool=Toolkit.getDefaultToolkit();
		//get the cotrol of the mouse tool in desktop
		Clipboard mouse=tool.getSystemClipboard();
		//copy the data into mouse
		mouse.setContents(path, null);
		//perform key operation on standalone app
		Thread.sleep(6000);
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(4000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	public void selectFromDropDown(WebElement element,String option)
	{
		Select sel=new Select(element);
//		List<WebElement> lst=sel.getOptions();
		sel.selectByVisibleText(option);
		
	}

}
