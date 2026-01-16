package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import BasePage.BaseP;

public class ProductPage extends BaseP {

	public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[@class='title']")
	public WebElement productText;

	public boolean isProductPageDisplayed() {
		return productText.isDisplayed();
	}

	@FindBy(xpath = "//div[@class='inventory_item']")
	public List<WebElement> productItems;

	public int productCount() {
		return productItems.size();
	}

	@FindBy(xpath = "//select[@class='product_sort_container']")
	public WebElement sortDropDown;

	public void sortByVisibleText(String text) {
		Select select = new Select(sortDropDown);
		select.selectByVisibleText(text);
	}

	public void sortbyValue(String value) {
		Select select = new Select(sortDropDown);
		select.selectByValue(value);
	}

	public void addProductByName(String productName) {
		WebElement product = driver.findElement(
				By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"));
		product.click();
	}

	public void addMultipleProducts(List<String> products) {
		for (String product : products) {
			addProductByName(product);
		}
	}

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	public WebElement cartBadge;

	public int getCartbadgeCount() {
		try {
			return Integer.parseInt(cartBadge.getText());
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollToProduct(String productName) {
		WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
	}

	@FindBy(xpath = "//div[@id='shopping_cart_container']")
	public WebElement cartIcon;

	public CartPage goToCart() {
		cartIcon.click();
		return new CartPage(driver);

	}

}
