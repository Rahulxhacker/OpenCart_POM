package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BasePage.BaseP;

public class CartPage extends BaseP {

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[@class='cart_item']")
	public List<WebElement> cartItems;

	@FindBy(xpath = "//button[@id='checkout']")
	public WebElement checkOutBtn;

	@FindBy(xpath = "//span[@class='title']")
	public WebElement yourCart;

	@FindBy(xpath = "//button[@id='continue-shopping']")
	public WebElement continuBtn;

	public int getCartCount() {
		return cartItems.size();
	}

	public CheckOutStepOnePage clickCheckOut() {
		checkOutBtn.click();
		return new CheckOutStepOnePage(driver);
	}

	public void removeProduct(String product) {
		WebElement remove = driver.findElement(
				By.xpath("//div[text()='" + product + "']/ancestor::div[@class='cart_item_label']//button"));
		remove.click();
	}

	public boolean isYourCartDisplayed() {
		return yourCart.isDisplayed();
	}

	public ProductPage clickContinueShopping() {
		continuBtn.click();
		return new ProductPage(driver);
	}

}
