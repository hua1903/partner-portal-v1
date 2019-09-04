package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utilities.Log;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.NAME, using = "userName")
	private WebElement txtbx_UserName;

	@FindBy(how = How.NAME, using = "password")
	private WebElement txtbxPassword;

	@FindBy(how = How.XPATH, using = "//button/span[contains(text(),'Login')]")
	private WebElement btnLogin;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Username or password invalid')]")
	private WebElement wrongUserNameOrPasswordMsg;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Please login again')]")
	private List<WebElement> msgLoginAgain;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Close')]")
	private WebElement btnClosedMsg;

	@FindBy(how = How.XPATH, using = "//div[@class='navbar-item']")
	private WebElement btn_Logo;

	@FindBy(how = How.XPATH, using = "//div[@class='content']")
	private WebElement anyMsg;

	public void gotto(WebDriver driver, String url) {
		try {
			driver.manage().window().maximize();
			driver.get(url);
		} catch (Exception ex) {
			throw new RuntimeException("Cannot direct to website");
		}

	}

	public boolean isBrowserOpen() {
		try {
			driver.getTitle();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void enterUserName(String name) throws Exception {
		try {
			txtbx_UserName.clear();
			txtbx_UserName.sendKeys(name);
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>The element of UserName textbox is changed.");
			Reporter.log("The element of UserName textbox is changed.");
		}
	}

	public void enterPassword(String pw) throws Exception {
		try {
			txtbxPassword.clear();
			txtbxPassword.sendKeys(pw);
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>The element of Password textbox is changed.");
			Reporter.log("The element of Password textbox is changed.");
		}
	}

	public void clickLogin() throws Exception {

		try {
			btnLogin.click();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>The element of Login button is changed.");
			Reporter.log("The element of Login button is changed.");
		}
	}

	public boolean notVisibleLoginAgainMsg() {
		return msgLoginAgain.isEmpty();
	}

	public boolean visibleLoginBtn() {
		try {
			return btnLogin.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public void clickCloseMsgBtn() throws Exception {

		try {
			btnClosedMsg.click();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >> The element of Close button in the pop-up >> Please Login >> is changed.");
			Reporter.log("The element of Close button in the pop-up >> Please Login >> is changed.");
		}
	}

	public boolean visibleWrongUserNameOrPassowrfMsg() {
		try {
			return wrongUserNameOrPasswordMsg.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean visibleBTBLogo() {
		try {
			return btn_Logo.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean visibleCloseBtn() {
		try {
			return btnClosedMsg.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public String getAnyMsg() {
		try {
			return anyMsg.getText();
		} catch (Exception ex) {
			return "";
		}
	}

}
