package Base; //Magento_eCommerce

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import utils.ExtentReportManager;



public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js;
	public static WebDriverWait wait;
	public ExtentTest logger; 
	public ExtentReports report = ExtentReportManager.getReportInstance();	
	public File file;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public FileOutputStream fos;
	
	public void driverSetup() {
		
		// Loading the properties
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/java/Config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Initializing the new chrome driver
		if (prop.getProperty("browserName").matches("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		// Initializing the new firefox driver
		if (prop.getProperty("browserName").matches("firefox")) {
			System.getProperty("WebDriver.gecko.driver", System.getProperty("user.dir")+"/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		// To maximize the window
		driver.manage().window().maximize();
		
		// Waiting time to page the load completely
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		// Adding driver waits to timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
	}
	
	// Method to open URL for smoke test
	public void openUrl() {
		
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		
	}
	
	// Function to show the failed test cases in the report
	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
	}

	// Function to show the passed test cases in the report
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}

	// Function to take Screenshot of screen
	public void Screenshot(String fileName) throws IOException {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+ "/Screenshot/" + fileName + ".png");
		Files.copy(srcFile, destFile);
	}
	
	// Function to Put Wait
	public void wait(Duration sec, By locator) {
		
		wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	/*public void ReadExcel() {
		
		String fileLocation = "./LoginCredentials/LoginCredentials.xlsx";

		try {
			workbook = new XSSFWorkbook(fileLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(1);
		
		for (int i = 0; i < 1; i++) {
			
			cell = row.getCell(0);
			String emailData = cell.getStringCellValue();
		}
		for (int j = 0; j < 1; j++) {
			
			cell = row.getCell(2);
			String passData = cell.getStringCellValue();
		}
		
	}*/
	
	// To input all data to the report
	public void endReport() {
		report.flush();
	}
	
	// To close the Browser
	public void closeBrowser() throws IOException {
		driver.quit();
	}

}
