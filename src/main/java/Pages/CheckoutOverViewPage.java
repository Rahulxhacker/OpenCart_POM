package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class CheckoutOverViewPage extends BaseP {

	public CheckoutOverViewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[@class='title']")
	public WebElement overViewHeader;

	public boolean isOverViewHeader() {
		return overViewHeader.isDisplayed();
	}

}
