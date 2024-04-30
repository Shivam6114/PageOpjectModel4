package com.crm.qa.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class CheckRequestStatus extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public CheckRequestStatus() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public HomePage viewAllIdentityTabs(WebDriver driver) {
		
		System.out.println("User application tab is now displaying");
		
		/* Read globalDataSheet[ROW][COLUMN] from dataSheet */
		/* Obtain the Username of the Identity we plan to search for */ 
		String viewIdentity = globalDataSheet[4][7].toString();
		System.out.println("Identity to view: " + viewIdentity);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
        
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='dropdown-toggle'])[2]")));
			
		WebElement menuElement = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[2]"));
		menuElement.click();
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Identity Warehouse")));
		WebElement identityWarehouse = driver.findElement(By.linkText("Identity Warehouse"));
		identityWarehouse.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='searchfield-1032-inputEl'])")));
		WebElement searchIdentityField = driver.findElement(By.xpath("(//input[@id='searchfield-1032-inputEl'])"));
		searchIdentityField.sendKeys(viewIdentity);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1119")));
		driver.findElement(By.id("ext-gen1119")).click();
		System.out.println("Only one (1) identity should be visible on the screen");
		
		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,viewIdentity)]")));
		WebElement clickIdentity = driver.findElement(By.xpath("//div[contains(.,viewIdentity)]")); 
		clickIdentity.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tab-1027-btnInnerEl")));
		WebElement clickAppAccountsTab = driver.findElement(By.id("tab-1027-btnInnerEl")); 
		clickAppAccountsTab.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Active Directory Local")));
		WebElement clickAppAccountsAD = driver.findElement(By.linkText("Active Directory Local")); 
		clickAppAccountsAD.click();
		
		/* Take a screen shot */
		//takeScreenShot(driver, "View_App_Acount");
		
		/* Scroll down the page 1900 pixels */
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 1900);");
        
		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The User: "+viewIdentity+" IdentityIIQ Application Accounts details should be visible on the screen");
		
		return new HomePage();
	}
	
	public static HomePage trackMyRequests(WebDriver driver) {
				
		System.out.println("Inside Track My Requests page");
		
		 // Wait for an element to be clickable (e.g., a button)
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-2.col-md-3.col-sm-6.quicklink-card.ng-scope")));
		
		
		driver.findElement(By.cssSelector(".quicklink-card-link.quicklink-track-requests")).click();

		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Access Requests page is now displaying");
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Track_My_Requests");		
		
		return new HomePage();
	}

	
	public static boolean retryingFindClick(WebDriver driver, By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
}
}
