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

import com.crm.qa.base.TestBase;

public class AppOwnerApproval extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public AppOwnerApproval() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	

	public HomePage approveAccessRequest(WebDriver driver) {
		/* The associated Manager login credentials value in the data sheet row should be used */
		/* Write function to pass in the Manager's userName and Password for any data sheet row */
	
		
		/* Read the Username value from the Excel data sheet  row=5 column=2 */
		//String userNameVal = userNameAppOwner;
 
		/* Read the password value from the Excel data sheet  row=5 column=3 */
		//String passwordVal = passwordAppOwner;
		
		//System.out.println("AppOwnerApproval login a values: "+userNameVal+" "+passwordVal);
		/* First log out of SailPoint */
		//logOut(driver);
		
		//LoginAsApprover(driver, userNameVal, passwordVal);
		
		//System.out.println("App Owner logged in as: "+userNameVal);
			
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//div[@class='card-title ng-binding'])[2]")));
  		WebElement approvalNotification = driver.findElement(By.xpath("(.//div[@class='card-title ng-binding'])[2]"));
		approvalNotification.click();
	
		System.out.println("Work Item page visible");
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//span[@class='badge bg-info ng-binding'])")));
		WebElement l = driver.findElement(By.xpath("(.//span[@class='badge bg-info ng-binding'])"));

		String s = l.getText();
		System.out.println("Value read on screen "+s);
	
		
		if(l.getText().contentEquals("0"))
		{
			
		  System.out.println("No Results is present on the approval page");
		  /* What to do if there are no workItems to approve */
		}
		else
		{
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnApproveAllApproval0")));
		  WebElement approveAllWorkItems = driver.findElement(By.id("btnApproveAllApproval0"));
		  
		  /* Take a screen shot */
		  //takeScreenShot(driver, "Approved_By_App_Owner");
			
		  approveAllWorkItems.click();
		  System.out.println("Work(s) Item approved");
		  
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//button[@class='btn  btn-info btn-default'])")));
		  WebElement completeWorkItems = driver.findElement(By.xpath("(.//button[@class='btn  btn-info btn-default'])"));
		  completeWorkItems.click();
		  /* What to do after I approve the work item and more workItems are listed to be approved */
		}
		
		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Work(s) Item COMPLETED");

		
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
