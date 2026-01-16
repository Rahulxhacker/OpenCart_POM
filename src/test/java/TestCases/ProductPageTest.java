package TestCases;

import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.LoginPage;
import Pages.ProductPage;
import Utils.DataProviderUtil;

public class ProductPageTest extends BaseT {

	ProductPage productPage;

	@Test(priority = 1, description = "Verify total product count", dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void verifyProductCountTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("userName"), data.get("passWord"));

		ProductPage productPage = new ProductPage(getDriver());

		Assert.assertEquals(productPage.productCount(), 6, data.get("msg"));
	}

	@Test(priority = 2, description = "Sort products by name A to Z", dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void sortByNameTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("userName"), data.get("passWord"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.sortByVisibleText(data.get("sortText"));

		Assert.assertTrue(productPage.productCount() > 0, data.get("errMsg"));
	}

	@Test(priority = 3, description = "Sort products by price low to high", dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void sortByPriceTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));
		ProductPage productPage = new ProductPage(getDriver());
		productPage.sortByVisibleText(data.get("sorttext"));
		Assert.assertTrue(productPage.productCount() > 0, data.get("msg"));
	}

	@Test(priority = 4, description = "add single product to cart", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void addSingleProductTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("userName"), data.get("password"));
		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName(data.get("productName"));
		int cartCount = productPage.getCartbadgeCount();
		Assert.assertEquals(cartCount, 1, data.get("msg"));
	}

	@Test(priority = 5, description = "Add multiple products to cart", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void addMultipleProductsTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("userName"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage
				.addMultipleProducts(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
						"Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"));

		int cartCount = productPage.getCartbadgeCount();
		Assert.assertEquals(cartCount, 6, data.get("msg"));

	}

	@Test(priority = 6, description = "verify cart badge count after adding products", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifyCartBadgeCountTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));
		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.addProductByName("Sauce Labs Bike Light");

		Assert.assertEquals(productPage.getCartbadgeCount(), 2, data.get("msg"));

	}

}
