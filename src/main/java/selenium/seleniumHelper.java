package selenium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class seleniumHelper {

	public static void waitElementVisible(WebDriver driver, int timeoutSecond, String stringXpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutSecond);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(stringXpath)));
		} catch (Exception ex) {

		}
	}

	public static void waitForPageLoaded(WebDriver driver) throws InterruptedException, IOException {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static void waitClickAble(WebDriver driver, int timeoutSecond, WebElement element) {
		try {
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, timeoutSecond);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception ex) {

		}
	}

	public static void waitSelectedAble(WebDriver driver, int timeoutSecond, WebElement element) {
		try {
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, timeoutSecond);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception ex) {

		}
	}

	public static boolean isClickable(WebDriver driver, int timeoutSecond, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutSecond);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean isLocated(WebDriver driver, int timeoutSecond, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutSecond);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static void waitLocated(WebDriver driver, int timeoutSecond, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutSecond);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception ex) {

		}
	}

	public static void doubleClick(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).doubleClick().perform();
	}

	public static void scrollDownToPixel(WebDriver driver, int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixel + ")");
	}

	public static void scrollDownToElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public static void scrollDownToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public static void waitForLoadingIcon(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		String xpathString = "//div[contains(@class,'react-sweet-progress-circle-outer')]";
		List<WebElement> loadingIcon = driver.findElements(By.xpath(xpathString));
		if (loadingIcon.size() > 0) {
			System.out.println("The website is loading...");
		}
		while (driver.findElements(By.xpath(xpathString)).size() > 0) {
			// don't do anything, only wait
		}

	}

	public static void hoverMouseOverEle(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
	}

	public static void sendKey(WebElement ele, String value) throws InterruptedException {
		Thread.sleep(500);
		ele.click();
		ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(1000);
		ele.sendKeys(Keys.DELETE);
		;
		Thread.sleep(1000);
		ele.sendKeys(value);

	}

	public static void openNewWindows(WebDriver driver, WebElement ele) {
		System.out.println("CONTROL+T");
		String key = Keys.chord(Keys.CONTROL + "t");
		ele.sendKeys(key);
		((JavascriptExecutor) driver).executeScript("window.open()");
	}

	public static boolean compareFileInChromeDownload(WebDriver driver, String fileName) throws Exception {
		Thread.sleep(5000);
		List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
		driver.get("chrome://downloads/");
		boolean status = false;
		try {
			WebElement item = driver.findElement(By.cssSelector("body/deep/downloads-item"));
			String text = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot;", item)
					.toString();
			System.out.println(text);
			if (text.contains(fileName))
				status = true;
			else
				status = false;
		} catch (Exception ex) {
			System.out.println(ex);
			status = false;
		}
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
		return status;
	}

	public static Select getSelectTag(WebDriver driver, WebElement ele) {
		try {
			Select s = new Select(ele);
			return s;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}

	public static String getHexColorCode(WebElement ele) {
		String color = ele.getCssValue("color");
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);

		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor;
	}
}
