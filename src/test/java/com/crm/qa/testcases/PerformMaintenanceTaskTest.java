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
import com.crm.qa.pages.PerformMaintenanceTask;

import com.crm.qa.util.TestUtil;

public class PerformMaintenanceTaskTest extends TestBase{
	PerformMaintenanceTask PerformMaintenanceTask;
	HomePage homePage;
	LoginToSailPoint LoginToSailPoint;

	
	public PerformMaintenanceTaskTest() throws EncryptedDocumentException, FileNotFoundException, IOException{
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		try {
			PerformMaintenanceTask = new PerformMaintenanceTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginToSailPoint = new LoginToSailPoint();
	}

	@Test(priority=0)
	public void PerformMaintenanceTaskTest(){
		homePage = LoginToSailPoint.login(prop.getProperty("usernameAppOwner"), prop.getProperty("passwordAppOwner"));
		homePage = PerformMaintenanceTask.PerformMaintenanceTasK(driver);
	
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}





}
