package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import selenium.seleniumHelper;
import utilities.Log;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Service Qualification')]")
	private WebElement serviceQualification;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Bureau Reports')]")
	private WebElement bureauReport;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Gap Payments')]")
	private WebElement gapPayments;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Accounts and Services')]")
	private WebElement accountService;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Logout')]/..")
	private List<WebElement> btnLogout;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Home')]/..")
	private List<WebElement> btnHome;

	@FindBy(how = How.XPATH, using = "//div[@class='navbar-item']")
	private WebElement btnLogo;

	@FindBy(how = How.XPATH, using = "//div[@class='inactive-bar']")
	private List<WebElement> btnInactiveHamburger;

	@FindBy(how = How.XPATH, using = "//div[@class='active-bar']")
	private WebElement btnActiveHamburger;

	@FindBy(how = How.XPATH, using = "//div[@class='dropdown-content']//div[contains(text(),'Service Qualification')]")
	private WebElement linkServiceQualification;

	@FindBy(how = How.XPATH, using = "//div[@class='dropdown-content']//div[contains(text(),'Bureau Reports')]")
	private WebElement linkBureauReports;

	@FindBy(how = How.XPATH, using = "//div[@class='dropdown-content']//div[contains(text(),'Gap Payments')]")
	private WebElement linkGapPayments;

	@FindBy(how = How.XPATH, using = "//div[@class='dropdown-content']//div[contains(text(),'Accounts and Services')]")
	private WebElement linkAccountService;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'react-sweet-progress-circle-outer')]")
	private List<WebElement> loadingIcon;

	public WebElement getElementHamburgerOption(String optionName) {
		return driver
				.findElement(By.xpath("//div[@class='dropdown-content']//div[contains(text(),'" + optionName + "')]"));
	}

	public WebElement getElementBoxName(String boxName) {
		return driver.findElement(By.xpath("//*[contains(text(),'" + boxName + "')]"));
	}

	public boolean visibleBoxes(String boxName) {
		try {
			return getElementBoxName(boxName).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean notVisibleLogout() {
		return btnLogout.isEmpty();
	}

	public boolean notVisibleHome() {
		return btnHome.isEmpty();
	}

	public void clickBoxBtn(String boxName) throws IOException, InterruptedException {
		try {
			getElementBoxName(boxName).click();
			// seleniumHelper.waitForLoadingIcon(driver);
		} catch (Exception ex) {
			Assert.fail("Cannot click on the box " + boxName);
		}

	}

	public void clickLogout() throws Exception {
		try {
			btnLogout.get(0).click();
		} catch (Exception ex) {
			Assert.fail("The element of Logout button is changed.");
		}
	}

	public void clickHome() throws Exception {
		try {
			seleniumHelper.waitForLoadingIcon(driver);
			Thread.sleep(1000);
			btnHome.get(0).click();
		} catch (Exception ex) {
			Assert.fail("The element of Home button is changed.");
		}
	}

	public boolean visibleBTBLogo() {
		try {
			return btnLogo.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickBtbLogo() throws Exception {
		try {
			seleniumHelper.waitForLoadingIcon(driver);
			btnLogo.click();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>Element of BTB logo is changed");
			Reporter.log("Element of BTB logo is changed");
		}
	}

	public void clickHamburgerBtn() throws Exception {
		try {
			btnInactiveHamburger.get(0).click();

		} catch (Exception ex) {
			Assert.fail("Hamburger element is changed.");
		}
	}

	public void clickCloseHamburgerBtn() throws Exception {
		try {
			btnActiveHamburger.click();

		} catch (Exception ex) {
			Assert.fail("Close btn of Hamburger element is changed.");
		}
	}

	public void clickBoxesInHamburger(String boxName) throws Exception {
		try {
			getElementHamburgerOption(boxName).click();
		} catch (Exception ex) {
			Assert.fail("Hamburger-" + boxName + " element is changed.");
		}
	}

	public boolean visibleBoxesInHamburger(String boxName) {
		try {
			return getElementHamburgerOption(boxName).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

}
