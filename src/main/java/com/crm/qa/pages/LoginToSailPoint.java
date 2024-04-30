package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class LoginToSailPoint extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public LoginToSailPoint(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	
	public HomePage login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		login.click();
		    	//JavascriptExecutor js = (JavascriptExecutor)driver;
		    	//js.executeScript("arguments[0].click();", login);
		    	
		return new HomePage();
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
}
