package com.crm.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.AppOwnerRejectPendingApproval;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginToSailPoint;
import com.crm.qa.pages.goToHomeScreen;
import com.crm.qa.pages.logOut;

public class AppOwnerRejectPendingApprovalTest extends TestBase{
	HomePage homePage;
	LoginToSailPoint LoginToSailPoint;
	AppOwnerRejectPendingApproval AppOwnerRejectPendingApproval;
	goToHomeScreen goToHomeScreen;
	logOut logOut;

	
	public AppOwnerRejectPendingApprovalTest() throws EncryptedDocumentException, FileNotFoundException, IOException{
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialization();
		try {
			AppOwnerRejectPendingApproval = new AppOwnerRejectPendingApproval();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginToSailPoint = new LoginToSailPoint();
	}

	@Test(priority=0)
	public void AppOwnerRejectApprovalTest(){
	    homePage = LoginToSailPoint.login(prop.getProperty("usernameAppOwner"), prop.getProperty("passwordAppOwner"));
		homePage = AppOwnerRejectPendingApproval.rejectApproval(driver);
	} 

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}





}
