package TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseT;
import Pages.LoginPage;
import Pages.ProductPage;
import Utils.DataProviderUtil;

public class LoginTest extends BaseT {

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void validLoginTest(Hashtable<String, String> data) throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("username"), data.get("password"));
		
		ProductPage productPage = new ProductPage(getDriver());
		Assert.assertTrue(productPage.isProductPageDisplayed(), data.get("err_msg"));
		
	}

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void invalidLoginTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("invalidU"), data.get("invalidP"));

		Assert.assertTrue(loginPage.doInvalidLogin(), data.get("invalidMsg"));
	}

	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "dp")
	public void lockedOutUserTest(Hashtable<String, String> data) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.doLogin(data.get("ulock"), data.get("ulockPass"));

		Assert.assertTrue(loginPage.dolockedOutUserValidation(), data.get("lock_err_Msg"));

	}

}
