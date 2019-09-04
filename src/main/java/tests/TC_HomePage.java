package tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import selenium.seleniumHelper;
import utilities.Log;

public class TC_HomePage extends BaseTest {
	LoginPage login;
	HomePage homepage;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		super.setUp();
		login = new LoginPage(driver);
		homepage = new HomePage(driver);
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

	@Test(priority = 3, dataProvider = "box")
	public void seeBox(String box) {
		if (homepage.visibleBoxes(box))
			Log.info("The box " + box + " is displayed under Home page");
		else
			Assert.fail("Don't see the box " + box + " under Home page");
	}

	@Test(priority = 4, dataProvider = "box")
	public void navigateToBox(String box) throws Exception {
		homepage.clickBoxBtn(box);
		Log.info("Clicked on box " + box);
		if (homepage.notVisibleHome())
			Assert.fail("Don't see Home button");
		else
			Log.info("See the Home button");
		if (homepage.notVisibleLogout())
			Assert.fail("Don't see Logout button");
		else
			Log.info("See the Logout button");
		homepage.clickHome();
		Log.info("Clicked on Home button");
		if (homepage.visibleBoxes(box))
			Log.info("The box " + box + " is displayed under Home page");
		else
			Assert.fail("Don't see the box " + box + " under Home page");

	}

	@DataProvider(name = "box")
	public static Object[][] boxes() {
		return new Object[][] { { "Service Qualification" }, { "Accounts" }, { "Product Application" },
				{ "Bureau Reports" }, { "Gap Payments" } };
	}

}
