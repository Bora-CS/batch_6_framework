package utilityLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import boraPages.Dashboard;
import boraPages.EducationPage;
import boraPages.ExpirencePage;
import boraPages.HomePage;
import boraPages.LoginPage;

public class BasePage {

	static WebDriver driver;
	private String url = "https://boratech.herokuapp.com/";
	
	
	public static SeleniumFunctionalMethod lib;

	public static HomePage home = new HomePage();
	public static LoginPage login = new LoginPage();
	public static Dashboard dashboard = new Dashboard();
	public static ExpirencePage expirence = new ExpirencePage();
	public static EducationPage edu = new EducationPage();

	@BeforeMethod
	public void startTest() {
		setUpDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		lib = new SeleniumFunctionalMethod(driver);

		lib.openUrl(url);
	}

	@AfterMethod
	public void endTest(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			lib.takeScreenShot(result.getName());
		}
		closeDriver();
	}

	
	
	
	
	
	private void closeDriver() {
		driver.close();
		driver.quit();
	}

	private void setUpDriver() {
		String os = System.getProperty("os.name");
		String driverPath = "";
		if (os.toLowerCase().startsWith("mac")) {
			driverPath = "src/main/resources/chromedriver91";
		} else if (os.toLowerCase().startsWith("windows")) {
			driverPath = "src/main/resources/chromedriver91.exe";
		}

		System.setProperty("webdriver.chrome.driver", driverPath);

		driver = new ChromeDriver();
	}

}
