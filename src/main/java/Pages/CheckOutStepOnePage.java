package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePage.BaseP;

public class CheckOutStepOnePage extends BaseP {

	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	public CheckOutStepOnePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[@class='title']")
	public WebElement checkOutPageHeader;

	public boolean isCheckOutStepOnePageDisplayed() {
		return checkOutPageHeader.isDisplayed();
	}

	@FindBy(xpath = "//input[@id='continue']")
	public WebElement continueBtn;

	@FindBy(xpath = "//button[@id='cancel']")
	public WebElement cancelBtn;

	@FindBy(xpath = "//h3[normalize-space()='Error: First Name is required']")
	public WebElement errMsg;

	public CheckoutOverViewPage enterDetails(String fname, String lname, String code) {

		WebElement firstName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='first-name']")));
		firstName.sendKeys(fname);

		WebElement lastName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='last-name']")));
		lastName.sendKeys(lname);

		WebElement zipCode = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='postal-code']")));
		zipCode.sendKeys(code);

		return verifyContinueBtn();
	}

	public boolean checkBlankSubmission() {
		continueBtn.click();
		return errMsg.isDisplayed();
	}

	public CartPage checkCancelBtn() {
		cancelBtn.click();
		return new CartPage(driver);
	}

	public CheckoutOverViewPage verifyContinueBtn() {
		continueBtn.click();
		return new CheckoutOverViewPage(driver);

	}

}
