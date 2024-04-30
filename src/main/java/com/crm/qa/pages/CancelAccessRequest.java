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

public class CancelAccessRequest extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public CancelAccessRequest() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public HomePage cancelAccessRequest(WebDriver driver) {
		
		System.out.println("Clicked the Track My Request button");
		 // Wait for an element to be clickable (e.g., a button)
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-2.col-md-3.col-sm-6.quicklink-card.ng-scope")));
		
		
		driver.findElement(By.cssSelector(".quicklink-card-link.quicklink-track-requests")).click();
		
		System.out.println("Clicked the Cancel Request button");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-white.btn-sm.ng-binding.ng-scope")));
		
		driver.findElement(By.cssSelector(".btn.btn-white.btn-sm.ng-binding.ng-scope")).click();
		System.out.println("Enter Reason for cancellation popup is now displaying");
		
		WebElement cancelReason = driver.findElement(By.id("commentTextArea"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("commentTextArea")));
		cancelReason.sendKeys("Manager requested the incorrect role for the employee");
		System.out.println("Reason for cancellation entered in COmment Text Area");
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Cancelled_Access_Request");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-danger.btn-default")));
		driver.findElement(By.cssSelector(".btn.btn-danger.btn-default")).click();

		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Request Canceled");
		
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
