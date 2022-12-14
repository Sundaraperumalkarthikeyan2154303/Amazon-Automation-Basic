package Case01;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonAuto {
	
	
	public static WebDriver driver;
	public static WebDriverWait wait;


	public static void main(String[] args) throws InterruptedException, IOException{
		
		WebDriver driver;
		System.out.println("Select Your Browser: 1.Chrome 2.Edge");
		Scanner sc=new Scanner(System.in);
		int choice=sc.nextInt();
		if(choice==1) {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium-java\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else {
			System.setProperty("webdriver.edge.driver", "C:\\selenium-java\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		System.out.println("Website Open Successfully");
		
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("mobile smartphones under 30000");
		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();
		System.out.println("Search Successfully");
		
		//Before Sorting
		WebElement beforeSorting = driver.findElement(By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[1]"));
		
		driver.findElement(By.xpath("//*[@id='a-autoid-0-announce']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Newest Arrivals")).click();
		
		//After Sorting
		WebElement afterSorting = driver.findElement(By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[1]"));
		  Select availableOptions = new Select(driver.findElement(By.id("s-result-sort-select")));
		  WebElement currOption = availableOptions.getFirstSelectedOption();
		  //printing the options count
		  List<WebElement> lst = availableOptions.getOptions();
		  System.out.println("The count of the options displayed is : "+lst.size());
		  //Verifying that  Newest Arrivals  option got selected correctly or not
		  String toCheck = currOption.getText();
		  String expectedText = "Newest Arrivals";
		  if(expectedText.matches(toCheck)) {
		   System.out.println("Newest Arrivals option is selected successfully.");
		  }else {
		   System.out.println("Newest Arrivals option is not selected.");
		  }
		  //validating the search string
		  if(beforeSorting == afterSorting) {
		   System.out.println("Test Case Failed : Newly arrived mobiles phones are not sorted.");
		  }else {
		   System.out.println("Test Case passed : Newly arrived mobiles phones are displayed.");
		  }
		  Thread.sleep(5000);
		  //Closing the browser
		  driver.quit();
	}
	
}
