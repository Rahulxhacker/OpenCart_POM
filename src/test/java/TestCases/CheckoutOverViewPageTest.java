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
import Pages.ThankYouPage;
import Utils.DataProviderUtil;

public class CheckoutOverViewPageTest extends BaseT {

	@Test(priority = 1, description = "Verify checkout overview details", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifycheckoutOverview(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.addProductByName("Sauce Labs Bike Light");

		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		checkOutStepOnePage.enterDetails(data.get("firstname"), data.get("lastname"), data.get("zipcode"));

		CheckoutOverViewPage checkoutOverViewPage = new CheckoutOverViewPage(getDriver());
		Assert.assertTrue(checkoutOverViewPage.isOverViewHeader(), "Checkout overview page not displayed");

		Assert.assertEquals(checkoutOverViewPage.getOverviewCount(), 2, "Overview items count mismatch");

		double calculatedTotal = checkoutOverViewPage.getTax() + checkoutOverViewPage.getItemTotal();
		Assert.assertEquals(checkoutOverViewPage.getTotalAmount(), calculatedTotal,
				"Total amount calculation mismatch");

	}

	@Test(priority = 2, description = "verify finish checkout flow", dataProvider = "dp", dataProviderClass = DataProviderUtil.class)
	public void verifyFinishCheckout(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.addProductByName("Sauce Labs Bike Light");

		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		checkOutStepOnePage.enterDetails(data.get("firstname"), data.get("lastname"), data.get("zipcode"));

		CheckoutOverViewPage checkoutOverViewPage = new CheckoutOverViewPage(getDriver());
		checkoutOverViewPage.checkFinishBtn();

		ThankYouPage thankYouPage = new ThankYouPage(getDriver());
		Assert.assertTrue(thankYouPage.isCheckoutHeader(), "order completion failed");
	}

}
