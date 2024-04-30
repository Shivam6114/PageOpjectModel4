package com.crm.qa.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginToSailPoint;

public class ADAccountAggregationTask extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginForm:accountId")
	WebElement username;
	
	@FindBy(id="loginForm:password")
	WebElement password;
	
	@FindBy(name="loginForm:loginButton")
	WebElement login;
	
	
	//Initializing the Page Objects:
	public ADAccountAggregationTask() throws Exception, FileNotFoundException, IOException{
		super();
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	
	public HomePage ADAccountAggregationTask(WebDriver driver) {
		/* The associated Manager login credentials value in the data sheet row should be used */
		/* Read the Username value from the Excel data sheet  row=5 column=2 */
		//String userNameVal = UserNameApproverManager;
 
		/* Read the password value from the Excel data sheet  row=5 column=3 */
		//String passwordVal = PasswordApproverManager;
		
		/* First logg out of SailPoint */
		//logOut(driver);
		
		//LoginAsApprover(driver, userNameVal, passwordVal);
		
		System.out.println("SailPoint Admin login was successful");
		String searchTask = "AD Account Aggregation";
		System.out.println("User application tab is now displaying");
		
		/* On the main menu, select Setup=>Tasks */
		WebDriverWait wait = new WebDriverWait(driver, 30);
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='dropdown-toggle'])[5]")));
		WebElement menuElement = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[5]"));
		menuElement.click();
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Setup_Tasks");
		
		/* Select 'Tasks' */	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tasks")));
		WebElement identityWarehouse = driver.findElement(By.linkText("Tasks"));

		/* Take a screen shot */
		identityWarehouse.click();
		//takeScreenShot(driver, "Select_Tasks");
		
		
		/* Enter 'Active Directory' in the search bar */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='tasksSearchField-inputEl'])")));
		WebElement searchIdentityField = driver.findElement(By.xpath("(//input[@id='tasksSearchField-inputEl'])"));
		searchIdentityField.sendKeys(searchTask);

		/* Take a screen shot */
		//takeScreenShot(driver, "Search_AD_Agg_Account");
		System.out.println("Task searched for: "+searchTask);

		/* Click the search icon */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1225")));
		driver.findElement(By.id("ext-gen1225")).click();
		System.out.println("Only one (1) task should be visible on the screen");
	
		/* Take a screen shot */
		//takeScreenShot(driver, "AD_Agg_Account");
		
		
		/* Call the wait.until 5 times 
		 * This will allow the Identity being searched to appear in row #1 
		 * */
		
		/* Wait 5 seconds to display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Click the 'Performance maintenance' task link in the results to edit the task */
		By taskElementID = By.id("spBodyPanel-innerCt");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spBodyPanel-innerCt")));
		WebElement taskElement = driver.findElement(By.id("spBodyPanel-innerCt"));
		
		Boolean taskElementFound = retryingFindClick(driver,taskElementID);
		if (taskElementFound)
		{		
		taskElement.click(); 
		System.out.println("Single Refresh Task found and clicked: "+searchTask);
		}	
		
		/* Click the 'Save and Execute' button */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])")));
		WebElement runTask = driver.findElement(By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])"));
		/* Take a screen shot */
		//takeScreenShot(driver, "AD_Agg_Account_edit");
		
		/* Run the AD Aggregation task */
		runTask.click();
		
		/* Click the 'OK' button to confirm the notification */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='button-1013-btnEl'])")));
		WebElement okButton = driver.findElement(By.xpath("(//button[@id='button-1013-btnEl'])"));
		okButton.click();
		
		/* Select the 'Task Results' tab to view the results of executed tasks */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='tab-1091-btnEl'])")));
		WebElement taskTabResults = driver.findElement(By.xpath("(//button[@id='tab-1091-btnEl'])"));
		taskTabResults.click();
		
		/* Enter 'AD Account Aggregation' in the search bar */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='resultsSearchField-inputEl'])")));
		WebElement taskTabResultsSearch = driver.findElement(By.xpath("(//input[@id='resultsSearchField-inputEl'])"));
		taskTabResultsSearch.sendKeys(searchTask);
		/* Take a screen shot */
		//takeScreenShot(driver, "search_AD_Agg_Account");
		
		/* Click the search icon */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1238")));
		driver.findElement(By.id("ext-gen1238")).click();
		
		System.out.println("The task execution results should be displaying on the screen");
		/* Take a screen shot */
		//takeScreenShot(driver, "AD_Agg_Account_results");
		
		return new HomePage();
	}
	
	public HomePage PerformMaintenanceTask(WebDriver driver) {
		/* The associated Manager login credentials value in the data sheet row should be used */
		/* Read the Username value from the Excel data sheet  row=5 column=2 */
		//String userNameVal = prop.getProperty("UserNameApproverManager");
 
		/* Read the password value from the Excel data sheet  row=5 column=3 */
		//String passwordVal = prop.getProperty("PasswordApproverManager");
		
		/* First log out of SailPoint */
		//logOut(driver);
		
		//LoginToSailPoint(driver, userNameVal, passwordVal);
		
		System.out.println("Manager login was successful");
		String searchTask = "Perform maintenance";
		System.out.println("User application tab is now displaying");
		
		/* On the main menu, select Setup=>Tasks */
		WebDriverWait wait = new WebDriverWait(driver, 30);
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='dropdown-toggle'])[5]")));
		WebElement menuElement = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[5]"));
		menuElement.click();
				
		/* Select 'Tasks' */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tasks")));
		WebElement identityWarehouse = driver.findElement(By.linkText("Tasks"));
		identityWarehouse.click();
		
		/* Enter 'Perform maintenance' in the search bar */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='tasksSearchField-inputEl'])")));
		WebElement searchIdentityField = driver.findElement(By.xpath("(//input[@id='tasksSearchField-inputEl'])"));
		searchIdentityField.sendKeys(searchTask);
		System.out.println("Task searched for: "+searchTask);

		/* Click the search icon */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1225")));
		driver.findElement(By.id("ext-gen1225")).click();
		
		/* Take a screen shot */
		//takeScreenShot(driver, "PerformMaintenanceTask");
		
		System.out.println("Only one (1) task should be visible on the screen");
		
		/* Call the wait.until 5 times 
		 * This will allow the Identity being searched to appear in row #1 
		 * */

		/* Wait 5 seconds for the page to catch up and display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Click the 'Performance maintenance' task link in the results to edit the task */
		By taskElementID = By.id("spBodyPanel-innerCt");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spBodyPanel-innerCt")));
		WebElement taskElement = driver.findElement(By.id("spBodyPanel-innerCt"));
		
		Boolean taskElementFound = retryingFindClick(driver,taskElementID);
		if (taskElementFound)
		{		
		taskElement.click(); 
		System.out.println("Perform Maintenance Task found and clicked: "+searchTask);
		}	
		
		/* Click the 'Save and Execute' button */
		By runTaskID = By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])")));
		WebElement runTask = driver.findElement(By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])"));
		
		Boolean runTaskFound = retryingFindClick(driver,runTaskID);
		if (runTaskFound)
		{		
			runTask.click();
			System.out.println("Perform Maintenance Save and Execute button found and clicked");
		}	
		
		
		/* Click the 'OK' button to confirm the notification */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='button-1013-btnEl'])")));
		WebElement okButton = driver.findElement(By.xpath("(//button[@id='button-1013-btnEl'])"));
		/* Take a screen shot */
		//takeScreenShot(driver, "Run_Perform_Maintenance_Task");
		
		okButton.click();
		
		/* Select the 'Task Results' tab to view the results of executed tasks */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='tab-1091-btnEl'])")));
		WebElement taskTabResults = driver.findElement(By.xpath("(//button[@id='tab-1091-btnEl'])"));
		taskTabResults.click();
		
		/* Enter 'Perform maintenance' in the search bar */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='resultsSearchField-inputEl'])")));
		WebElement taskTabResultsSearch = driver.findElement(By.xpath("(//input[@id='resultsSearchField-inputEl'])"));
		taskTabResultsSearch.sendKeys(searchTask);
		
		/* Click the search icon */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1238")));
		driver.findElement(By.id("ext-gen1238")).click();
		
		/* Wait 5 seconds for the page to catch up and display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The task execution results should be displaying on the screen");
		
		return new HomePage();
	}

	public HomePage SingleIdentityRefreshTask(WebDriver driver){
		/* The associated Manager login credentials value in the data sheet row should be used */
		/* Read the Username value from the Excel data sheet  row=5 column=2 */
		String userNameVal = prop.getProperty("UserNameApproverManager");
 
		/* Read the password value from the Excel data sheet  row=5 column=3 */
		String passwordVal = prop.getProperty("PasswordApproverManager");
		/* Identity to refresh */
		String refreshIdentityFilter = "email == \""+globalDataSheet[11][6].toString()+"\"";
		
		/* First log out of SailPoint */
		//logOut(driver);
		
		//LoginAsApprover(driver, userNameVal, passwordVal);
		
		System.out.println("Manager login was successful");
		String searchTask = "Refresh Identity Cube";
		System.out.println("User application tab is now displaying");
		
		/* On the main menu, select Setup=>Tasks */
		WebDriverWait wait = new WebDriverWait(driver, 40);
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='dropdown-toggle'])[5]")));
		WebElement menuElement = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[5]"));
		menuElement.click();
		
		/* Select 'Tasks' */	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Tasks")));
		WebElement identityWarehouse = driver.findElement(By.linkText("Tasks"));
		identityWarehouse.click();
		
		/* Enter 'Refresh Identity Cube' in the search bar */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='tasksSearchField-inputEl'])")));
		WebElement searchIdentityField = driver.findElement(By.xpath("(//input[@id='tasksSearchField-inputEl'])"));
		searchIdentityField.sendKeys(searchTask);
		
		/* Take a screen shot */
		//takeScreenShot(driver, "Refresh_Identity_Cube");
		
		System.out.println("Task searched for: "+searchTask);

		/* Click the search icon */
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1225")));
		WebElement searchResults = driver.findElement(By.id("ext-gen1225"));
		searchResults.click();
		System.out.println("Only one (1) task should be visible on the screen");
		
		/* Call the wait.until more than one time, if needed 
		 * This will allow the Identity being searched to appear in row #1 
		 * */
		
		
		
		/* Click the 'Refresh Identity Cube' task link in the results to edit the task */
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("x-grid-col-resizer-gridcolumn-1022")));
		//WebElement taskElement = driver.findElement(By.className("x-grid-col-resizer-gridcolumn-1022"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spBodyPanel-innerCt")));
		//WebElement taskElement = driver.findElement(By.id("spBodyPanel-innerCt"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[@class='x-grid-cell x-grid-cell-gridcolumn-1022 x-grid-cell-first'])")));
		//WebElement refreshElement = driver.findElement(By.xpath("(//td[@class='x-grid-cell x-grid-cell-gridcolumn-1022 x-grid-cell-first'])"));

		/* Wait 5 seconds for the page to catch up and display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		By taskElementID = By.id("gridview-1026-bd-Identity");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gridview-1026-bd-Identity")));
		WebElement taskElement = driver.findElement(By.id("gridview-1026-bd-Identity"));
		
		Boolean taskElementFound = retryingFindClick(driver,taskElementID);
		if (taskElementFound)
		{		
		taskElement.click(); 
		System.out.println("Single Refresh Task found and clicked: "+searchTask);
		}		
		/* 
		 * Enter the User email as the single User filter 
		 *  email == 'first.last@example.com'
		 */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='editForm:filter_str'])")));
		WebElement searchByIdentityFilter = driver.findElement(By.xpath("(//input[@id='editForm:filter_str'])"));
		searchByIdentityFilter.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),refreshIdentityFilter);
		System.out.println("Refresh identity filter: "+refreshIdentityFilter);
		
		/* Wait 3 seconds to view the details of the User being refreshed */
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Click the 'Save and Execute' button */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])")));
		WebElement runTask = driver.findElement(By.xpath("(//button[@id='editForm:validateBeforeExecuteButton'])"));
		runTask.click();
		
		/* Click the 'OK' button to confirm the notification */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='button-1013-btnEl'])")));
		WebElement okButton = driver.findElement(By.xpath("(//button[@id='button-1013-btnEl'])"));
		okButton.click();
		
		/* Select the 'Task Results' tab to view the results of executed tasks */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='tab-1091-btnEl'])")));
		WebElement taskTabResults = driver.findElement(By.xpath("(//button[@id='tab-1091-btnEl'])"));
		taskTabResults.click();
		
		/* Enter 'Refresh Identity Cube' in the search bar */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='resultsSearchField-inputEl'])")));
		WebElement taskTabResultsSearch = driver.findElement(By.xpath("(//input[@id='resultsSearchField-inputEl'])"));
		taskTabResultsSearch.sendKeys(searchTask);
		
		/* Click the search icon */
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1238")));
		driver.findElement(By.id("ext-gen1238")).click();
		
		/* Wait 5 seconds for the page to catch up and display the results */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The task execution results should be displaying on the screen");
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
