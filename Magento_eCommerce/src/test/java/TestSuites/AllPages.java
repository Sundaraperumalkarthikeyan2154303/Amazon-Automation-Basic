package TestSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import Pages.LoginPage;

public class AllPages extends Base{
	
	LoginPage lp = new LoginPage();
	
	@BeforeTest
	public void driverSetup() {
		logger = report.createTest("Executing Test Cases");

		lp.driverSetup();
		reportPass("Browser is Invoked");
	}
	
	@Test(priority=0)
	public void testCases() {
		lp.openUrl();
		lp.login();
		reportPass("All steps passed successfuly");
	}
	
	@AfterTest
	public void closeBrowser() throws IOException {
		reportPass("Browser is closed successfuly");
		lp.endReport();
		lp.closeBrowser();
	}
	

}
