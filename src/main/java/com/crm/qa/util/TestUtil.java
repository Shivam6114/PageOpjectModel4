package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import com.crm.qa.base.TestBase;

@SuppressWarnings("unused")
public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "/Users/info/OneDrive/Documents/GitHub/AmariKaiPageObjectModel/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	

	
	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	/*
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	*/
	
	public static String[][] getData(String excelSheetName)
			throws IOException, EncryptedDocumentException, FileNotFoundException {
	
		
		
		File f = new File("C:\\Users\\info\\Documents\\excelData\\dataSheet.xlsx");
		//System.out.println("Passed 1");
	
		FileInputStream fls = new FileInputStream(f);
		//System.out.println("Passed 2");
	
		XSSFWorkbook wb = new XSSFWorkbook(fls);
		//System.out.println("Passed 3");
	
		// Creating a Sheet object using the sheet Name
		//XSSFSheet sheet=wb.getSheet("login");
	
		int numsheet = wb.getNumberOfSheets();
		XSSFSheet sheetName = null;
		sheetName = wb.getSheet(excelSheetName);
		for (int i = 0; i < numsheet; i++)
	
		{
	
			if (wb.getSheetName(i).equalsIgnoreCase("login"))
	
			{
	
				sheetName = wb.getSheetAt(i);
	
			}
	
		}
	
		System.out.println("Selected the Sheet: " + excelSheetName.toString());
		int totalRows = sheetName.getLastRowNum();
		System.out.println("Total rows: " + totalRows);
	
		// Create a row object to retrieve row at index 1
		// XSSFRow row2=sheetName.getRow(1);
		// XSSFRow rowCells = sheetName.getRow(1);
	
		// System.out.println("Passed 5");
		// Create a cell object to retreive cell at index 5
		// XSSFCell cell = row2.getCell(5);
		XSSFCell cell = null;
		int totalCols = sheetName.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Total cols: " + totalCols);
		// System.out.println("Passed 6");
		// Get the address in a variable
	
		DataFormatter format = new DataFormatter();
	
		String[][] testData = new String[totalRows][totalCols];
		
		/* Write the output to a log file */
		// loop through the data sheet
		for (int i = 1; i <= totalRows; i++) {
			cell = sheetName.getRow(i).getCell(0);
			// String processFlag = cell.getStringCellValue();
	
			// System.out.println("Process value is :"+ processFlag +"\n");
			if (cell.getStringCellValue().equals("Y")) {
				for (int j = 0; j < totalCols; j++) {
	
					testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
					if (j == 0) {
						System.out.print("Row: " + (i + 1));
					}
					System.out.print(testData[i - 1][j] + ", ");
					if (j == (totalCols - 1)) {
						System.out.println("|");
					}
				}
			} else {
				System.out.println("Bypassed row: " + (i + 1));
			}
		}
	
		// wb.close();
		// System.out.println("Passed 8");
		// fls.close();
		// System.out.println("Passed 9");
	
		return testData;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void takeScreenShot(WebDriver driver, String screenShotName) {
		 // Initialize WebDriver (you can use other drivers too)
       //WebDriver driver = new FirefoxDriver();
       //driver.get("http://www.amarikailong.com");

       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
		   LocalDateTime now = LocalDateTime.now();
		   System.out.println(dtf.format(now));
		String screenShotFileName  = screenShotName+dtf.format(now)+".png";
		
       // Capture the screenshot
       File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

       // Save the screenshot to a file
       try {
           FileUtils.copyFile(scrFile, new File("screenshots/"+screenShotFileName));
           System.out.println("Screenshot: "+screenShotFileName+" saved successfully!");
       } catch (Exception e) {
       	 System.err.println("Error saving screenshot: " + e.getMessage());
       }

       // Close the WebDriver
       //driver.quit();
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
		takeScreenShot(driver, "GoToHomeScreen");
		
		System.out.println("SailPoint Home screen should be visible");
		//driver.close();
	}
	
	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		// Check for jQuery on the page, add it if need be
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(5000);

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(5000);

		// jquery-growl w/ no frills
		js.executeScript("$.growl({ title: 'GET', message: '/' });");
//'"+color+"'"
		if (messageType.equals("error")) {
			js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
		}else if(messageType.equals("info")){
			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
		}else if(messageType.equals("warning")){
			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		}else
			System.out.println("no error message");
		// jquery-growl w/ colorized output
//		js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
//		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		Thread.sleep(5000);
	}

}
