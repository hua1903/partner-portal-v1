package tests;

import java.io.File;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import pages.HomePage;
import pages.LoginPage;
import pages.ServiceQualificationPage;
import selenium.seleniumHelper;
import utilities.CreateJsonFile;
import utilities.Log;

public class TC_SearchLocationID extends BaseTest {
	LoginPage login;
	HomePage homepage;
	ServiceQualificationPage sqpage;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		super.setUp();
		login = new LoginPage(driver);
		homepage = new HomePage(driver);
		sqpage = new ServiceQualificationPage(driver);
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
	public void navigateToBox(String box) throws Exception {
		homepage.clickBoxBtn(box);
		Log.info("Clicked on box " + box);

	}

	@Test(priority = 4)
	public void seeSearchTbox() throws Exception {
		if (sqpage.notVisibleSearchSQ())
			Assert.fail("Don't see Search textbox under SQ page");
		else
			Log.info("See Search textbox under SQ page");

	}

	@Test(priority = 5)
	@Parameters({ "value" })
	public void searchLocationID(String value) throws Exception {
		sqpage.enterSQSearchBox(value);
		Log.info("Entered value: " + value);
		sqpage.clickSearch();
		Log.info("Clicked on Search button");
		Thread.sleep(5000);
	}

	@Test(priority = 6)
	@Parameters({ "searchField", "value" })
	public void createJsonFileSQPage(String searchField, String value) throws Throwable {
		if (ServiceQualificationPage.ignoreCase) {

		} else {
			try {
				if (searchField.equalsIgnoreCase("Address")) {
					searchField = "Location Id";
					value = sqpage.getValueLocIDAddress().toUpperCase();
					CreateJsonFile.createJsonFileSQPage(searchField, value);
				} else if (searchField.equalsIgnoreCase("Address of AAPT")) {
					searchField = "Telstra ID";
					value = sqpage.getValueTelstraIDAddress(0).toUpperCase();
					CreateJsonFile.createJsonFileSQPage(searchField, value);
				} else if (searchField.equalsIgnoreCase("Address of AAPTs")) {
					int totalRadio = sqpage.getTotalRatioAAPT();
					searchField = "Telstra ID";
					for (int i = 0; i < totalRadio; i++) {
						value = sqpage.getValueTelstraIDAddress(i).toUpperCase();
						CreateJsonFile.createJsonFileSQPage(searchField, value);
					}
				} else {
					CreateJsonFile.createJsonFileSQPage(searchField, value.toUpperCase());
				}

			} catch (Exception ex) {
				Log.info("cannot create the json file for the field " + searchField + " with the value " + value);
				System.out.println(ex);
				throw new RuntimeException(
						"cannot create the json file for the field " + searchField + " with the value " + value);
			}
			ServiceQualificationPage.ignoreCase = false;
		}
	}

	@Test(priority = 7)
	@Parameters({ "value" })
	public void verifySingleAddress(String value) throws Throwable {
		if (!ServiceQualificationPage.ignoreCase) {
			String valueLOCID = sqpage.getValueNBNLOC(0);
			File jsonfile = new File(System.getProperty("user.dir") + "//Json " + valueLOCID + ".js");
			int totalHeaderNBN = sqpage.getTotalHeaderLoc();
			String headerNBNWeb = sqpage.getHeaderNBNLOC(0);
			String valueNBNWeb = "";
			String headerJson = "";
			String valueJson = "";
			if (headerNBNWeb.equalsIgnoreCase("Location Id")) {
				valueNBNWeb = sqpage.getValueNBNLOC(0);
				String locJS = JsonPath.read(jsonfile, "$." + "data.rawSQResultList[0].value").toString();
				String[] valueArr = locJS.split(":");
				String LocID = valueArr[0].trim().toString();
				if (valueNBNWeb.equalsIgnoreCase(LocID)) {
					Log.info("Location Id " + LocID + " is displayed on the website");
				} else {
					Assert.fail("Location Id " + LocID + " is NOT displayed on the website");
				}
			}
			headerNBNWeb = sqpage.getHeaderNBNLOC(2);
			if (headerNBNWeb.equalsIgnoreCase("Serviceability")) {
				valueNBNWeb = sqpage.getValueNBNLOC(2);
				valueJson = JsonPath.read(jsonfile, "$." + "data.rawSQResultList[1].value").toString();
				if (valueNBNWeb.equalsIgnoreCase(valueJson)) {
					Log.info("The field Serviceability " + valueJson + " is displayed on the website");
				} else {
					Assert.fail("The field Serviceability " + valueJson + " is NOT displayed on the website");
				}
			}
			int indexJson = 2;
			for (int i = 3; i < totalHeaderNBN; i++) {
				headerNBNWeb = sqpage.getHeaderNBNLOC(i);
				if (headerNBNWeb.equalsIgnoreCase("Lead Time")) {
					System.out.println("ignore Lead Time");
					i++;
				}
				headerNBNWeb = sqpage.getHeaderNBNLOC(i).replaceAll(" ", "");
				headerJson = JsonPath.read(jsonfile, "$." + "data.rawSQResultList[" + indexJson + "].name").toString()
						.replaceAll(" ", "");
				headerJson = headerJson.replaceAll("-", "");
				if (headerNBNWeb.equalsIgnoreCase(headerJson)) {
					valueNBNWeb = sqpage.getValueNBNLOC(i);
					valueJson = JsonPath.read(jsonfile, "$." + "data.rawSQResultList[" + indexJson + "].value")
							.toString();
					if (valueJson.equalsIgnoreCase(valueNBNWeb)) {
						Log.info("The value " + valueNBNWeb + " of the field " + headerJson + " is display on website");
					} else {
						Assert.fail("The field " + headerNBNWeb + " with value " + valueNBNWeb
								+ " is NOT same as value on the Json");
					}
				} else {
					Assert.fail("The field " + headerNBNWeb + " with value " + valueNBNWeb
							+ " is NOT same as value on the Json");
				}
				indexJson++;

			}
			ServiceQualificationPage.ignoreCase = false;
		} else
			Log.info("There is no data, so pass through this step.");

	}

	@DataProvider(name = "box")
	public static Object[][] boxes() {
		return new Object[][] { { "Service Qualification" } };
	}

}
