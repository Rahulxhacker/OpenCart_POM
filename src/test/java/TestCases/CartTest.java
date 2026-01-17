package TestCases;

import java.util.Hashtable;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.CartPage;
import Pages.CheckOutStepOnePage;
import Pages.LoginPage;
import Pages.ProductPage;
import Utils.DataProviderUtil;

public class CartTest extends BaseT {

	@Test(priority = 1, description = "Verify if land on cart page")
	public void verifyLandOnCartPage() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin("standard_user", "secret_sauce");

		ProductPage productPage = new ProductPage(getDriver());
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());

		Assert.assertTrue(cartPage.isYourCartDisplayed(), "Cart page does not load");

	}

	@Test(priority = 2, description = "verify cart items count after adding products", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifyCartItemsCountTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addMultipleProducts(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));

		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		Assert.assertEquals(cartPage.getCartCount(), 2, data.get("msg"));
	}

	@Test(priority = 3, description = "Remove product from cart and verify count", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void removeProductFromCartTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addMultipleProducts(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.removeProduct("Sauce Labs Backpack");

		Assert.assertEquals(cartPage.getCartCount(), 1, data.get("msg"));
	}

	@Test(priority = 4, description = "Verify cart badge count matches cart items", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifyCartBadgeAndCartItemMatch(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addMultipleProducts(
				List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));

		int badgeCount = productPage.getCartbadgeCount();

		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		Assert.assertEquals(cartPage.getCartCount(), badgeCount, data.get("msg"));

	}

	@Test(priority = 5, description = "Verify continue shopping navigates back to product page")
	public void continueShoppingTest() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin("standard_user", "secret_sauce");

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addMultipleProducts(
				List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"));
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		productPage = cartPage.clickContinueShopping();

		Assert.assertEquals(productPage.productCount(), 6, "user not navigated back to product page");

	}

	@Test(priority = 6, description = "Verify checkout button is working or not")
	public void verifyCheckOut() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin("standard_user", "secret_sauce");

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Bike Light");
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		Assert.assertTrue(checkOutStepOnePage.isCheckOutStepOnePageDisplayed(), "Checkout btn is not working");
	}

}
