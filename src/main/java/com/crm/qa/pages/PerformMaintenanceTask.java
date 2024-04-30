package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class PerformMaintenanceTask extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	
	
	// Initializing the Page Objects:
	public HomePage PerformMaintenanceTasK(WebDriver driver) {
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
