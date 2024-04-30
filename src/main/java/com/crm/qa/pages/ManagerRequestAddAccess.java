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

public class ManagerRequestAddAccess extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public ManagerRequestAddAccess() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public HomePage addAccess(WebDriver driver) {
	
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-2.col-md-3.col-sm-6.quicklink-card.ng-scope")));

		WebElement hamburger = driver.findElement(By.id("quicklinkButton"));
		hamburger.click();
		System.out.println("Clicked the home page hamburger button");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quickLinkCategoryAccess")));

		WebElement dropdown = driver.findElement(By.id("quickLinkCategoryAccess"));

		System.out.println("Trying to click the Manage_Access button");
		dropdown.click();

		System.out.println("Clicked the Manage Access button");
		

		System.out.println("Trying to click the Manage_Access button");

		System.out.println("Trying to click the Manage_User_Access button");

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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectBtn-1")));
		WebElement selectUser = driver.findElement(By.id("selectBtn-1"));
		selectUser.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessRequestFooterNextBtn")));
		WebElement selectUserNext = driver.findElement(By.id("accessRequestFooterNextBtn"));
		selectUserNext.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessSearchText")));
		WebElement accessSearchUserText = driver.findElement(By.id("accessSearchText"));

		/* Enter the role to be requested from the data spreadsheet */
		String roleRequested = globalDataSheet[2][5];
		System.out.println("Role to request: " + roleRequested);
		accessSearchUserText.sendKeys(roleRequested.toString());
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Role_To_Assign");
		
		// accessSearchUserText.sendKeys("IIQAD\\Cloneable Domain Controllers");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessSearchBtn")));
		WebElement accessSearchUserBtn = driver.findElement(By.id("accessSearchBtn"));
		accessSearchUserBtn.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-cell")));
		WebElement accessRoleSelect = driver.findElement(By.className("header-cell"));
		accessRoleSelect.click();
		System.out.println("Clicked the Manage_User_Access button");

		// WebElement selectOwner =
		// driver.findElement(By.cssSelector("div[class='col-xs-12'"));
		// selectOwner.click();

		/* ***** Check if the Select Account Pop-Up display ***** */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='modal-content']/div/div/div[2]/div[2]/div/div/div/button/i")));
		WebElement selectOwner = driver.findElement(By.xpath("//div[@id='modal-content']/div/div/div[2]/div[2]/div/div/div/button/i"));
		selectOwner.click();
		//WebElement selectOwner = driver.findElement(By.xpath("//div[@id='modal-content']/div/div/div[2]/div[2]/div/div/div/button/i"));
		//selectOwner.click();

		System.out.println("Clicked the Select Owner button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saveBtn")));
		WebElement rowSaveBtn = driver.findElement(By.id("saveBtn"));
		rowSaveBtn.click();
		System.out.println("Clicked the saveBtn");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accessRequestFooterNextBtn")));
		WebElement accessRoleNextBtn = driver.findElement(By.id("accessRequestFooterNextBtn"));
		accessRoleNextBtn.click();
		System.out.println("Clicked the next button after selecting AD Group");
		

		/* Review and Submit the Access Request */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitBtn")));
		WebElement reviewSubmitBtn = driver.findElement(By.id("submitBtn"));
		reviewSubmitBtn.click();
		System.out.println("Reviewed and submitted the access request");

		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,-350)", "");

		// Locating element by link text and store in variable "Element"
		// WebElement Element = driver.findElement(By.className("v-middle h5
		// header-cell-text"));
		// v-middle h5 header-cell-text
		// Scrolling down the page till the element is found
		// js.executeScript("arguments[0].scrollIntoView();", Element);
		// js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		// Logout and Close the Browser
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-2.col-md-3.col-sm-6.quicklink-card.ng-scope")));
		//logOut(driver);
		//driver.close();


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
