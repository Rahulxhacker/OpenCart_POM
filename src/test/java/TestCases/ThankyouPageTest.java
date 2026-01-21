package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.CartPage;
import Pages.CheckOutStepOnePage;
import Pages.CheckoutOverViewPage;
import Pages.LoginPage;
import Pages.ProductPage;
import Pages.ThankYouPage;

public class ThankyouPageTest extends BaseT {

	@Test(priority = 1, description = "verify thankyou page")
	public void verifyThankyoupage() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin("standard_user", "secret_sauce");

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		checkOutStepOnePage.enterDetails("Rahul", "Kashyap", "482001");

		CheckoutOverViewPage checkoutOverViewPage = new CheckoutOverViewPage(getDriver());
		checkoutOverViewPage.checkFinishBtn();

		ThankYouPage thankYouPage = new ThankYouPage(getDriver());
		Assert.assertTrue(thankYouPage.isCheckoutHeader(), "Thankyou header is not displayed");
		Assert.assertTrue(thankYouPage.checkThankyoulogo(), "Thankyou logo is not displayed");
		Assert.assertTrue(thankYouPage.checkThankYouText(), "Thankyou text is not displayed");

	}

	@Test(priority = 2, description = "verify Backhome button")
	public void verifyBackHomeBtn() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin("standard_user", "secret_sauce");

		ProductPage productPage = new ProductPage(getDriver());
		productPage.addProductByName("Sauce Labs Backpack");
		productPage.goToCart();

		CartPage cartPage = new CartPage(getDriver());
		cartPage.clickCheckOut();

		CheckOutStepOnePage checkOutStepOnePage = new CheckOutStepOnePage(getDriver());
		checkOutStepOnePage.enterDetails("Rahul", "Kashyap", "482001");

		CheckoutOverViewPage checkoutOverViewPage = new CheckoutOverViewPage(getDriver());
		checkoutOverViewPage.checkFinishBtn();

		ThankYouPage thankYouPage = new ThankYouPage(getDriver());
		productPage = thankYouPage.checkBackhomeBtn();
		Assert.assertTrue(productPage.isProductPageDisplayed(),
				"falied to load productpage after cliking backhome btn");

	}

}
