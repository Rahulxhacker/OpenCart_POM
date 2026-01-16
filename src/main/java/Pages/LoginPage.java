package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class LoginPage extends BaseP {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//input[@id='user-name']")
	public WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement passWord;

	@FindBy(xpath = "//input[@id='login-button']")
	public WebElement loginBtn;

	@FindBy(xpath = "//h3[contains(text(),'Epic sadface: Username and password do not match a')]")
	public WebElement invalidMsg;

	@FindBy(xpath = "//h3[contains(text(),'Epic sadface: Sorry, this user has been locked out')]")
	public WebElement locked_out_user;

	public ProductPage doLogin(String uName, String uPass) {
		userName.sendKeys(uName);
		passWord.sendKeys(uPass);
		loginBtn.click();
		return new ProductPage(driver);
	}

	public boolean doInvalidLogin() {
		return invalidMsg.isDisplayed();
	}

	public boolean dolockedOutUserValidation() {
		return locked_out_user.isDisplayed();
	}

}
