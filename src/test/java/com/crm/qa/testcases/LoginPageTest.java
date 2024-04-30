package com.crm.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.LoginToSailPoint;

public class LoginPageTest extends TestBase{
	LoginToSailPoint LoginToSailPoint;
	HomePage homePage;

	public LoginPageTest() throws EncryptedDocumentException, FileNotFoundException, IOException{
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		LoginToSailPoint = new LoginToSailPoint();
	}

	@Test(priority=1)
	public void verifyPageTitleTest(){
		String title = LoginToSailPoint.validateLoginPageTitle();
		Assert.assertEquals(title, "SailPoint IdentityIQ");
	}

	
	@Test(priority=2)
	public void LoginToSailPointTest(){
		homePage = LoginToSailPoint.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=3)
	public void LogoutOfSailPointTest(){
		homePage = LoginToSailPoint.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage = LoginToSailPoint.logOut(driver);
	
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}





}
