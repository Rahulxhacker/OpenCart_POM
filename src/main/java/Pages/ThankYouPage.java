package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class ThankYouPage extends BaseP {

	public ThankYouPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[@class='title']")
	public WebElement checkOutCompleteHeader;

	public boolean isCheckoutHeader() {
		return checkOutCompleteHeader.isDisplayed();
	}

	@FindBy(xpath = "//button[@id='back-to-products']")
	public WebElement backHomeBtn;

	public ProductPage checkBackhomeBtn() {
		backHomeBtn.click();
		return new ProductPage(driver);
	}

	@FindBy(xpath = "//img[@alt='Pony Express']")
	public WebElement thankYouOrderLogo;

	public boolean checkThankyoulogo() {
		return thankYouOrderLogo.isDisplayed();
	}

	@FindBy(xpath = "//h2[normalize-space()='Thank you for your order!']")
	public WebElement thankYouText;

	public boolean checkThankYouText() {
		return thankYouText.isDisplayed();
	}

}
