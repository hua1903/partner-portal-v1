package tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import selenium.seleniumHelper;
import utilities.Log;

public class TC_LoginFail extends BaseTest {
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
	public void loginFail(String name, String pw) throws Exception {
		login.enterUserName(name);
		login.enterPassword(pw);
		login.clickLogin();
		seleniumHelper.waitForLoadingIcon(driver);
		if (login.visibleWrongUserNameOrPassowrfMsg())
			Log.info("I see the message: Username or password invalid");
		else
			Assert.fail("I don't see the message: Username or password invalid");
	}

}
