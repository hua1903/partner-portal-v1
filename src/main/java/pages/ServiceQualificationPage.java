package pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Helper;
import utilities.Log;

public class ServiceQualificationPage {
	WebDriver driver;
	public File jsonfile;
	public static boolean ignoreCase = false;

	public ServiceQualificationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'react-sweet-progress-circle-outer')]")
	private List<WebElement> loadingIcon;

	@FindBy(how = How.XPATH, using = "//div[@class='content']")
	private WebElement warningMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Close')]")
	private WebElement btnClose;

	// Searching Location Id
	@FindBy(how = How.XPATH, using = "//input[contains(@class,'search-input')]")
	private List<WebElement> txtSearchSQ;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Search')]")
	private List<WebElement> btnsearch;

	@FindBy(how = How.XPATH, using = "//tr/td/span[contains(text(),'Location Id')]")
	private WebElement titleLocationID;

	@FindBy(how = How.XPATH, using = "//tr/td[contains(text(),'Address')]")
	private WebElement titleAddressLocID;

	@FindBy(how = How.XPATH, using = "//input[@name='emailTo']")
	private WebElement txtEmailAddress;

	@FindBy(how = How.XPATH, using = "//textarea[@name='emailMessage']")
	private WebElement txtEmailMsg;

	@FindBy(how = How.XPATH, using = "//div/button[contains(@class,'btn-share-result')]")
	private WebElement btnSend;

	@FindBy(how = How.XPATH, using = "//p[text()='Details']/../following-sibling::*[@class='address-item']//table/tbody/tr/td[1]")
	private List<WebElement> headerNBNLOC;

	@FindBy(how = How.XPATH, using = "//p[text()='Details']/../following-sibling::*[@class='address-item']//table/tbody/tr/td[2]")
	private List<WebElement> valueNBNLOC;

	@FindBy(how = How.XPATH, using = "//p[text()='Details']/../following-sibling::*[@class='address-item']//table/tbody/tr/td[1]")
	private List<WebElement> headerNBNAddress;

	@FindBy(how = How.XPATH, using = "//p[text()='Details']/../following-sibling::*[@class='address-item']//table/tbody/tr/td[2]")
	private List<WebElement> valueNBNAddress;

	public boolean visibleWarningMsg() {
		try {
			return btnClose.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public String getWarninMsg() {
		return warningMessage.getText();
	}

	public boolean notVisibleSearchSQ() {
		return txtSearchSQ.isEmpty();
	}

	public int getTotalHeaderLoc() {
		return headerNBNLOC.size();
	}

	public String getHeaderNBNLOC(int i) {
		return headerNBNLOC.get(i).getText();
	}

	public String getValueNBNLOC(int i) {
		return valueNBNLOC.get(i).getText();
	}

	public int getTotalHeaderAddress() {
		return headerNBNAddress.size();
	}

	public String getHeaderNBNAddress(int i) {
		return headerNBNAddress.get(i).getText();
	}

	public String getValueNBNAddress(int i) {
		return valueNBNAddress.get(i).getText();
	}

	public boolean visibleHeaderOfLocID(String header) {
		try {
			switch (header) {
			case ("Location Id"):
				return titleLocationID.isDisplayed();
			case ("Address"):
				return titleAddressLocID.isDisplayed();
			default:
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	// Search Telsa ID
	@FindBy(how = How.XPATH, using = "//tr/th//*[contains(text(),'Address')]")
	private WebElement titleAddressTelstraID;

	@FindBy(how = How.XPATH, using = "//tr/td[@class='align-center']/span[2]")
	private List<WebElement> valueAddressTelstraID;

	@FindBy(how = How.XPATH, using = "//tr/th/*[contains(text(),'Access Method')]")
	private WebElement titleAccessMed;

	@FindBy(how = How.XPATH, using = "//tr/td[2]/span")
	private List<WebElement> valueAccessMed;

	@FindBy(how = How.XPATH, using = "//tr/th/*[contains(text(),'Access Type')]")
	private WebElement titleAccessType;

	@FindBy(how = How.XPATH, using = "//tr/td[3]/span")
	private List<WebElement> valueAccessType;

	@FindBy(how = How.XPATH, using = "//tr/th/*[contains(text(),'Price Zone')]")
	private WebElement titlePriZone;

	@FindBy(how = How.XPATH, using = "//tr/td[4]/span")
	private List<WebElement> valuePriZone;

	@FindBy(how = How.XPATH, using = "//tr/th/*[contains(text(),'Speed Value')]")
	private WebElement titleSpeedValue;

	@FindBy(how = How.XPATH, using = "//tr/td[5]/span")
	private List<WebElement> valueSpeedValue;

	@FindBy(how = How.XPATH, using = "//div[@class='multiple-address-msg']")
	private WebElement msgMultpleValues;

	@FindBy(how = How.XPATH, using = "//div[@class='ant-table-footer']/div")
	private WebElement labelValueDistance;

	@FindBy(how = How.XPATH, using = "//tr/td/li/label")
	private List<WebElement> radioBtnAAPT;

	public void waitLoading() throws InterruptedException {
		boolean status = false;
		while (loadingIcon.size() > 0) {
			if (!status) {
				System.out.println("The website is loading...");
				status = true;
			} else {
			}
		}

	}

	public boolean visibleHeaderOfTelstraID(String header) throws InterruptedException {
		try {
			switch (header) {
			case ("Address"):
				return titleAddressTelstraID.isDisplayed();
			case ("Access Method"):
				return titleAccessMed.isDisplayed();
			case ("Access Type"):
				return titleAccessType.isDisplayed();
			case ("Price Zone"):
				return titlePriZone.isDisplayed();
			case ("Speed Value"):
				return titleSpeedValue.isDisplayed();
			default:
				return false;
			}

		} catch (Exception ex) {
			return false;
		}
	}

	public int numListAddressTelstraID() {
		return valueAddressTelstraID.size();
	}

	public String valueAddressTelstraID(int index) {
		return valueAddressTelstraID.get(index).getText();
	}

	public int numListAccessMed() {
		return valueAccessMed.size();
	}

	public String valueAccessMed(int index) {
		return valueAccessMed.get(index).getText();
	}

	public int numListAccessType() {
		return valueAccessType.size();
	}

	public String valueAccessType(int index) {
		return valueAccessType.get(index).getText();
	}

	public int numListPriZone() {
		return valuePriZone.size();
	}

	public String valuePriZone(int index) {
		return valuePriZone.get(index).getText();
	}

	public int numListSpeedValue() {
		return valueSpeedValue.size();
	}

	public String valueSpeedValue(int index) {
		return valueSpeedValue.get(index).getText();
	}

	public int getTotalSpeedValueOfOneRow(int index) {
		List<WebElement> ele = driver
				.findElements(By.xpath("//tr[" + index + "]/td[contains(@class,'tesltra-speed-value')]//li"));
		return ele.size();
	}

	public String getSpeedValue(int index1, int index2) {
		WebElement ele = driver.findElement(
				By.xpath("//tr[" + index1 + "]/td[contains(@class,'tesltra-speed-value')]//li[" + index2 + "]"));
		return ele.getText();
	}

	// page factory for searching single address
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Location Id')]")
	private WebElement titleLOCID;
	@FindBy(how = How.XPATH, using = "//tr/th/span[contains(text(),'Address')]")
	private WebElement titleAddress;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Serviceable')]")
	private WebElement titleServiceableAddress;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'New Development Charge')]")
	private WebElement titleDevChargAddress;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Serviceability Class')]")
	private WebElement titleServiceabilityClass;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Primary Access')]")
	private WebElement titlePrimaryAccess;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'CopperPair ID')]")
	private WebElement titleCopperPairID;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Upstream Max/Min')]")
	private WebElement titleUpstream;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Downstream Max/Min')]")
	private WebElement titleDownstream;
	// get values
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Location Id')]/following-sibling::td")
	private WebElement valueLOCID;
	// @FindBy(how=How.XPATH, using = "//tbody[@class='ant-table-tbody']/tr")
	@FindBy(how = How.XPATH, using = "//li/label")
	private List<WebElement> valueAddress;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Serviceable')]/following-sibling::td")
	private WebElement valueServiceableAddress;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'New Development Charge')]/following-sibling::td")
	private WebElement valueDevChargAddress;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Serviceability Class')]/following-sibling::td")
	private WebElement valueServiceabilityClass;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Primary Access')]/following-sibling::td")
	private WebElement valuePrimaryAccess;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'CopperPair ID')]/following-sibling::td")
	private WebElement valueCopperPairID;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Upstream Max/Min')]/following-sibling::td")
	private WebElement valueUpstream;
	@FindBy(how = How.XPATH, using = "//tbody/tr/td[contains(text(),'Downstream Max/Min')]/following-sibling::td")
	private WebElement valueDownstream;
	@FindBy(how = How.XPATH, using = "//td[contains(@class,'tooltip')]")
	private WebElement valueServiceClass;

	public boolean visibleHeaderOfAddress(String header) {
		try {
			switch (header) {
			case ("Location Id"):
				return titleLOCID.isDisplayed();
			case ("Address"):
				return titleAddress.isDisplayed();
			case ("Serviceable"):
				return titleServiceableAddress.isDisplayed();
			case ("New Development Charge"):
				return titleDevChargAddress.isDisplayed();
			case ("Serviceability Class"):
				return titleServiceabilityClass.isDisplayed();
			case ("Primary Access"):
				return titlePrimaryAccess.isDisplayed();
			case ("CopperPair ID"):
				return titleCopperPairID.isDisplayed();
			case ("Upstream Max per Min"):
				return titleUpstream.isDisplayed();
			case ("Downstream Max per Min"):
				return titleDownstream.isDisplayed();
			default:
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public String getValueLocIDAddress() {
		try {
			return valueLOCID.getText().toString();
		} catch (Exception ex) {
			System.out.println(ex);
			Log.error("There is >> no value >> for Location Id");
			throw new RuntimeException("There is >> no value >> for Location Id");
		}
	}

	public String getValueTelstraIDAddress(int i) {
		try {
			return radioBtnAAPT.get(i).getText();
		} catch (Exception ex) {
			System.out.println(ex);
			Log.error("There is >> no value >> for Telstra ID");
			throw new RuntimeException("There is >> no value >> for Telstra ID");
		}
	}

	public void ClickOnRadioAAPT(int i) {
		try {
			radioBtnAAPT.get(i).click();
			waitLoading();
		} catch (Exception ex) {

		}
	}

	public int getTotalRatioAAPT() {
		return radioBtnAAPT.size();
	}

	public String getValueSingleAddress() {
		try {
			return valueAddress.get(0).getText();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>There is >> no value >> for Address");
			throw new RuntimeException("There is >> no value >> for Address");
		}
	}

	public int totalAddressRow() {
		return valueAddress.size();
	}

	public void clickRadioAddress(int i) throws InterruptedException {
		valueAddress.get(i).click();
		waitLoading();
	}

	// get value for verifying single address
	public String getValueServiceable() {
		try {
			return valueServiceableAddress.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: There is >> no value >> for Serviceable");
			throw new RuntimeException("There is >> no value >> for Serviceable");
		}
	}

	public String getValueDevCharge() {
		try {
			return valueDevChargAddress.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: There is >> no value >> for New Development Charge");
			throw new RuntimeException("There is >> no value >> for New Development Charge");
		}
	}

	public String getValueServiceabilityClass() {
		try {
			return valueServiceabilityClass.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>There is >> no value >> for Serviceability Class");
			throw new RuntimeException("There is >> no value >> for Serviceability Class");
		}
	}

	public String getValuePrimaryAccess() {
		try {
			return valuePrimaryAccess.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>There is >> no value >> for Primary Access");
			throw new RuntimeException("There is >> no value >> for Primary Access");
		}
	}

	public String getValueCopperPairID() {
		try {
			return valueCopperPairID.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>There is >> no value >> for CopperPair ID");
			throw new RuntimeException("There is >> no value >> for CopperPair ID");
		}
	}

	public String getValueUpstream() {
		try {
			return valueUpstream.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>There is >> no value >> for Upstream Max/Min");
			throw new RuntimeException("There is >> no value >> for Upstream Max/Min");
		}
	}

	public String getValueDownstream() {
		try {
			return valueDownstream.getText().toString();
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>There is >> no value >> for Downstream Max/Min");
			throw new RuntimeException("There is >> no value >> for Downstream Max/Min");
		}
	}

	// Search box
	public void enterSQSearchBox(String value) throws Exception {
		try {
			txtSearchSQ.get(0).clear();
			Thread.sleep(1000);
			txtSearchSQ.get(0).sendKeys(value);
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>The element of search box is changed");
			throw new RuntimeException("The element of search box is changed");
		}
	}

	public void clickSearch() throws Exception {
		try {
			btnsearch.get(0).click();
			waitLoading();
			if (visibleWarningMsg()) {
				Log.warn(getWarninMsg());
				btnClose.click();
				ignoreCase = true;

			}
		} catch (Exception ex) {
			Log.error("Stop the scenario: >>The element of search box in Service Qualification page is changed");
			System.out.println(ex);
			throw new RuntimeException("The element of search box in Service Qualification page is changed");

		}
	}

	public boolean visibleMsgMultpleResults() {
		try {
			return msgMultpleValues.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean visibleDistance() {
		try {
			return labelValueDistance.isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	public String getValueDistance() {
		String textDistance = labelValueDistance.getText();
		String snValue = Helper.splitSpecificSymbold(textDistance, "Distance To Exchange: ")[1].trim();
		String distanceValue = Helper.splitSpecificSymbold(snValue, " ")[0].trim();
		return distanceValue;
	}

	public String getServiceClass() {
		try {
			return valueServiceClass.getText().trim();
		} catch (Exception ex) {
			return "";
		}
	}

	public void hoverMouseOverServiceClass() throws InterruptedException, IOException {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(valueServiceClass).moveToElement(valueServiceClass).click().build().perform();
		} catch (Exception ex) {
			String messageOfError = "Stop the scenario: Don't see the tool tip";
			Assert.fail(messageOfError);
		}
	}

	public String getValueOfToolTip() throws InterruptedException, IOException {
		try {
			return valueServiceClass.getAttribute("data-tooltip");
		} catch (Exception ex) {
			return "";
		}
	}

	public String getServiceClassSystem(String serviceClassNumber) throws InterruptedException, IOException {
		switch (serviceClassNumber) {
		case ("1"):
			return "The site is serviceable by fibre, with no PCD or NTD in place.";
		case ("2"):
			return "The site is serviceable by fibre, PCD is installed, no NTD in place.";
		case ("3"):
			return "The site is serviceable by fibre, PCD and NTD are installed.";
		case ("4"):
			return "The site is planned to be serviceable by fixed wireless NBN.";
		case ("5"):
			return "The site is serviceable by fixed wireless NBN, no antenna or NTD in place.";
		case ("6"):
			return "The site is serviceable by fixed wireless NBN, antenna and NTD are installed.";
		case ("7"):
			return "The site is planned to be serviceable by satellite.";
		case ("8"):
			return "The site is serviced by satellite (dish/NTD not installed).";
		case ("9"):
			return "The site is services by satellite (dish/HTD already installed).";
		case ("10"):
			return "Site is planned to be serviceable by copper (FTTN or FTTB).";
		case ("11"):
			return "Site is serviceable by copper, copper lead-in required.";
		case ("12"):
			return "Site is serviceable by copper, jumpering is required.";
		case ("13"):
			return "Site is serviceable by copper, all infrastructure is in place.";
		case ("20"):
			return "Site is serviceable by cable (HFC).";
		case ("21"):
			return "The property is within the HFC footprint, no drop, wall plate or NTD.";
		case ("22"):
			return "The property is within the HFC footprint, drop in place, no wall plate or NTD.";
		case ("23"):
			return "The property is within the HFC footprint, drop and wall plate in place, no NTD.";
		case ("24"):
			return "The property is within the HFC footprint, drop, wall plate and NTD in place.";
		case ("30"):
			return "The property will be serviced by FTTC technology.";
		case ("31"):
			return "The property is within the FTTC footprint, copper lead in is required.";
		case ("32"):
			return "The property is within the FTTC footprint. Copper lead in is present but not connected to DPU. An NCD is required.";
		case ("33"):
			return "The property is within the FTTC footprint. Property is connected to DPU but an NCD is required.";
		case ("34"):
			return "The property is within the FTTC footprint. It has previously been transferred to NBN and can transfer to a new provider without an installation appointment.";
		default:
			return "";
		}
	}

	public boolean visibleAccessMethodInSystemList(String accessMed) {
		switch (accessMed) {
		case ("Off Net ADSL2+ (MSAN)"):
			return true;
		case ("On Net ADSL2+ Annex A (Type ii)"):
			return true;
		case ("Off Net Telstra L2IG"):
			return true;
		case ("Off Net iiNet ADSL2+"):
			return true;
		case ("Off Net AAPT SHDSL"):
			return true;
		case ("Off Net AAPT ADSL2+"):
			return true;
		default:
			return false;
		}
	}

	// Email function
	public void enterEmailTo(String value) throws InterruptedException, IOException {
		try {
			txtEmailAddress.sendKeys(value);

		} catch (Exception ex) {
			Assert.fail("The element of the field Email To is changed");
		}
	}

	public void enterEmailMsg(String value) throws InterruptedException, IOException {
		try {
			txtEmailMsg.sendKeys(value);

		} catch (Exception ex) {
			Assert.fail("The element of the field Email Message is changed");
		}
	}

	public void sendEmail() throws InterruptedException, IOException {
		try {
			btnSend.click();
		} catch (Exception ex) {
			Assert.fail("The element of the Send Email button is changed");
		}
	}

}
