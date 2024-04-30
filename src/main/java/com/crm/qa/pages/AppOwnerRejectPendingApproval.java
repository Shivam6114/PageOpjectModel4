package com.crm.qa.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.crm.qa.pages.LoginToSailPoint;

import com.crm.qa.base.TestBase;

public class AppOwnerRejectPendingApproval extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public AppOwnerRejectPendingApproval() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	

	public HomePage rejectApproval(WebDriver driver) {
		/* The associated Manager login credentials value in the data sheet row should be used */
		/* Write function to pass in the Manager's userName and Password for any data sheet row */
	
		
		/* Read the Username value from the Excel data sheet  row=5 column=2 */
		//String userNameVal = UserNameApproverManager;
 
		/* Read the password value from the Excel data sheet  row=5 column=3 */
		//String passwordVal = PasswordApproverManager;
		
		/* First logg out of SailPoint */
		//logOut(driver);
		
		//LoginAsApprover(driver, userNameVal, passwordVal);
		System.out.println("App OWner login was successful");
			
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//div[@class='card-title ng-binding'])[2]")));
  
		WebElement approvalNotification = driver.findElement(By.xpath("(.//div[@class='card-title ng-binding'])[2]"));
		
		approvalNotification.click();
	
		System.out.println("Work Item page visible");
		
		//if(driver.findElement(By.xpath("(.//span[@class='badge bg-info ng-binding'])")))
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//span[@class='badge bg-info ng-binding'])")));
		
		WebElement l = driver.findElement(By.xpath("(.//span[@class='badge bg-info ng-binding'])"));
		//WebElement l = driver.findElement(By.xpath("//div/section/div/div/header/div/div/span/h1/span"));
		//WebElement l = driver.findElement(By.xpath(".//span[@id='workItemCountBadgeDesktop']"));
		String s = l.getText();
		System.out.println("Value read on screen "+s);
	
		
		if(l.getText().contentEquals("0"))
		{
			
		  System.out.println("No Results is present on the approval page");
		  /* What to do if there are no workItems to approve */
		}
		else
		{
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnRejectApproval0Item0")));
		  WebElement approveAllWorkItems = driver.findElement(By.id("btnRejectApproval0Item0"));
		  
		  /* Take a screen shot */
		  //takeScreenShot(driver, "App_Owner_Reject_Access_Request");
			
		  approveAllWorkItems.click();
		  System.out.println("Work(s) Item rejected");
		  
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//button[@class='btn  btn-info btn-default'])")));
		  WebElement completeWorkItems = driver.findElement(By.xpath("(.//button[@class='btn  btn-info btn-default'])"));
		  completeWorkItems.click();
		  /* What to do after I approve the work item and more workItems are listed to be approved */
		}
		

		System.out.println("Rejected Work(s)Item COMPLETED");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//span[@class='badge bg-info ng-binding'])")));
		WebElement r = driver.findElement(By.xpath("(.//span[@class='badge bg-info ng-binding'])"));
		String t = r.getText();
		System.out.println("Value read on screen "+t);
	
		  
		if(r.getText().contentEquals("0"))
		{
			/* If there  nomore approvals listed then stop and go to the Home screen */
		  System.out.println("There are no more approvals to reject, heading to the Home screen");
		  goToHomeScreen(driver);
		 }else
		 {
			/* If there are more approvals listed then stop and go ahead to log out */
			 System.out.println("I see more approvals to reject, stopping and logging out");
		  logOut(driver);
		  }
		
		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public static HomePage logOut(WebDriver driver) {
		System.out.println("User wants to log off of SailPoint");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameMenu")));

		WebElement logoffMenu = driver.findElement(By.id("usernameMenu"));
		logoffMenu.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
		WebElement selectLogOffBtn = driver.findElement(By.linkText("Logout"));
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Logging_Out");
		
		selectLogOffBtn.click();
		
		System.out.println("User is logging off of SailPoint");
		return new HomePage();
	}
	
	public static void goToHomeScreen(WebDriver driver) {
		/* Go to the SailPoint 'Home' screen */
		
		System.out.println("Searching for the SailPoint Home screen button");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
        
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//a[@class='menuitem'])")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//a[@class='menuitem'])")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//a[@class='menuitem'])")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//a[@class='menuitem'])")));
			
		WebElement menuElement = driver.findElement(By.xpath("(.//a[@class='menuitem'])"));
		menuElement.click();
		
		/* Take a screen shot */
		//takeScreenShot(driver, "GoToHomeScreen");
		
		System.out.println("SailPoint Home screen should be visible");
		//driver.close();
	}
}
