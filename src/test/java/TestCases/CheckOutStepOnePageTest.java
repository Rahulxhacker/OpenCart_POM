package TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.CartPage;
import Pages.CheckOutStepOnePage;
import Pages.CheckoutOverViewPage;
import Pages.LoginPage;
import Pages.ProductPage;
import Utils.DataProviderUtil;

public class CheckOutStepOnePageTest extends BaseT {

	@Test(priority = 1, description = "verifying form section", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verfiyFormDetails(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("loginusername"), data.get("loginpassword"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		checkOutStepOnePage.enterDetails(data.get("firstname"), data.get("lastname"), data.get("zipcode"));

		CheckoutOverViewPage checkoutOverViewPage = new CheckoutOverViewPage(getDriver());

		Assert.assertTrue(checkoutOverViewPage.isOverViewHeader(), data.get("errmsg"));

	}

	@Test(priority = 2, description = "verifying cancel button functionality", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifyCancelBtn(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		cartPage = checkOutStepOnePage.checkCancelBtn();

		Assert.assertTrue(cartPage.getCartCount() > 0, "cancel btn is not working");

	}

	@Test(priority = 3, description = "verifying blank form submission")
	public void verifyBlankFormSubmit() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin("standard_user", "secret_sauce");

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		Assert.assertTrue(checkOutStepOnePage.checkBlankSubmission(), "form getting submit without entering any data");
	}

}
