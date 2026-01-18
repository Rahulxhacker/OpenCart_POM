package BaseTest;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utils.ExcelReader;

public class BaseT {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public ExcelReader excel = new ExcelReader(
			"C:\\Users\\Rahul Kashyap\\PracticeAllConcepts\\OpenCart_POM\\src\\test\\resources\\excel\\Swag_lab_data.xlsx");

	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeMethod
	public void setUp() {
		ChromeOptions ops = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ops.setExperimentalOption("prefs", prefs);
		ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		ops.addArguments("--disable-notifications");
		ops.addArguments("incognito");
		driver.set(new ChromeDriver(ops));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get("https://www.saucedemo.com/");
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}

}
