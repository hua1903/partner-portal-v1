package tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import selenium.seleniumHelper;
import utilities.Log;

public class TC_LoginSuccess extends BaseTest {
	LoginPage login;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		super.setUp();
		login = new LoginPage(driver);
	}

	@Test(priority = 1)
	@Parameters({ "url" })
	public void gotoWeb(String url) {
		login.gotto(driver, url);
	}

	@Test(priority = 2)
	@Parameters({ "sUsername", "sPassword" })
	public void loginSuccess(String name, String pw) throws Exception {
		login.enterUserName(name);
		login.enterPassword(pw);
		login.clickLogin();
		seleniumHelper.waitForLoadingIcon(driver);
		if (login.visibleBTBLogo())
			Log.info("Login success");
		else
			Assert.fail("Cannot login");
	}

}
