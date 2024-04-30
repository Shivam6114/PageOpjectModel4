package com.crm.qa.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

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

public class ManagerRequestRemoveAccess extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public ManagerRequestRemoveAccess() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public HomePage removeAccess(WebDriver driver) {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-2.col-md-3.col-sm-6.quicklink-card.ng-scope")));

		WebElement hamburger = driver.findElement(By.id("quicklinkButton"));
		hamburger.click();
		System.out.println("Clicked the home page hamburger button");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quickLinkCategoryAccess")));
		WebElement dropdown = driver.findElement(By.id("quickLinkCategoryAccess"));
		System.out.println("Trying to click the Manage_Access button");
		dropdown.click();

		System.out.println("Clicked the Manage Access button");
		

		// find the hyperlink using its link text
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Manage User Access")));
		WebElement link = driver.findElement(By.linkText("Manage User Access"));
		// click on the Manage_User_Access hyperlink
		link.click();
		

		System.out.println("Clicked the Manage_User_Access button");
		System.out.println("Opening Manage User Access page");
		

		// WebElement selectUser = driver.findElement(By.linkText("Manage User
		// Access"));
		/* Hard code select Abby Sanders as the #1 checkbox for now. Add checkbox beside the searched name */ 
		/* 
		 * Use something like: 
		 * String roleRequested = globalDataSheet[13][5]; 
		 * 
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectBtn-1")));
		WebElement selectUser = driver.findElement(By.id("selectBtn-1"));
		selectUser.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessRequestFooterNextBtn")));
		WebElement selectUserNext = driver.findElement(By.id("accessRequestFooterNextBtn"));
		selectUserNext.click();

		/* Select the Remove feature to display the User's assigned roles/entitlements */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//li[@id='removeAccessBtn']")));
		WebElement selectRemoveAccessTab = driver.findElement(By.xpath(".//li[@id='removeAccessBtn']"));
		selectRemoveAccessTab.click();
		System.out.println("Clicked the Remove Access button");
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Remove_User_Access");
		
		/* Select the role/entitlement to be REMOVED */ 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class='btn btn-sm btn-rounded v-middle m-r-sm ng-isolate-scope btn-white']")));
		WebElement selectRoleToRemove = driver.findElement(By.xpath(".//button[@class='btn btn-sm btn-rounded v-middle m-r-sm ng-isolate-scope btn-white']"));
		selectRoleToRemove.click();

		/* Click the Next button */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessRequestFooterNextBtn")));
		WebElement accessRoleNextBtn = driver.findElement(By.id("accessRequestFooterNextBtn"));
		accessRoleNextBtn.click();
		System.out.println("Clicked the next button after selecting AD Group");
		
		/* Review and Submit the access removal request */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitBtn")));
		WebElement reviewSubmitBtn = driver.findElement(By.id("submitBtn"));
		reviewSubmitBtn.click();
		System.out.println("Reviewed and submitted the access request");

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
