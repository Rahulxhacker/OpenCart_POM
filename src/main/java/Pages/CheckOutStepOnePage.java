package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class CheckOutStepOnePage extends BaseP {

	public CheckOutStepOnePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[@class='title']")
	public WebElement checkOutPageHeader;

	public boolean isCheckOutStepOnePageDisplayed() {
		return checkOutPageHeader.isDisplayed();
	}

}
