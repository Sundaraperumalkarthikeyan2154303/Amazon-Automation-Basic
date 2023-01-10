package Pages;

import org.openqa.selenium.By;

import Base.Base;

public class LoginPage extends Base{
	
	By signin = By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a");
	By email = By.xpath("//*[@id=\"email\"]");
	By pass = By.xpath("//*[@id=\"pass\"]");
	By submit = By.xpath("//*[@id=\"send2\"]");
	
	public void login() {
		
		driver.findElement(signin).click();
		wait(20,signin);
		
		logger = report.createTest("Login into Magento_eCommerce_Website.");
		wait(20,email);
		driver.findElement(email).sendKeys("nithishspk123@gmail.com");
		
		wait(20,pass);
		driver.findElement(pass).sendKeys("LMspk@123");
		
		wait(20,submit);
		driver.findElement(submit).click();
		
		
	}

	private void wait(int i, By signin2) {
		// TODO Auto-generated method stub
		
	}


}
