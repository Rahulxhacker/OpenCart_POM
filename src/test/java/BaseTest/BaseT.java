package BaseTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Utils.ExcelReader;

public class BaseT {

	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public ExcelReader excel = new ExcelReader(
			"C:\\Users\\Rahul Kashyap\\PracticeAllConcepts\\OpenCart_POM\\src\\test\\resources\\excel\\Swag_lab_data.xlsx");

	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeTest
	public void setUp() throws MalformedURLException {
		ChromeOptions ops = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ops.setExperimentalOption("prefs", prefs);
		ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		ops.addArguments("--disable-notifications");
		ops.addArguments("incognito");
		driver.set(new RemoteWebDriver(URI.create("http://192.168.47.116:4444/wd/hub").toURL(), ops));
//		driver.set(new ChromeDriver(ops));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get("https://www.saucedemo.com/");
	}

	@AfterTest
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

}
