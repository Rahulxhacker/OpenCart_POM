package Pages;

import java.util.List;

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

	@FindBy(xpath = "//button[@id='finish']")
	public WebElement finishBtn;

	public ThankYouPage checkFinishBtn() {
		finishBtn.click();
		return new ThankYouPage(driver);
	}

	@FindBy(xpath = "//button[@id='cancel']")
	public WebElement cancelBtn;

	public ProductPage chekcCancelBtn() {
		cancelBtn.click();
		return new ProductPage(driver);
	}

	@FindBy(className = "cart_item")
	public List<WebElement> overviewItems;

	public int getOverviewCount() {
		return overviewItems.size();
	}

	@FindBy(className = "summary_subtotal_label")
	public WebElement itemTotal;

	public double getItemTotal() {
		return Double.parseDouble(itemTotal.getText().replaceAll("[^0-9.]", ""));
	}

	@FindBy(className = "summary_tax_label")
	public WebElement tax;

	public double getTax() {
		return Double.parseDouble(tax.getText().replaceAll("[^0-9.]", ""));
	}

	@FindBy(className = "summary_total_label")
	public WebElement total;

	public double getTotalAmount() {
		return Double.parseDouble(total.getText().replaceAll("[^0-9.]", ""));
	}

}
