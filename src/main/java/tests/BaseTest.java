package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.util.concurrent.Uninterruptibles;

public class BaseTest {

	protected WebDriver driver;

	@BeforeSuite
	public void initialDelay() {
		// intentionally added this as chrome/firefox containers take few ms to
		// register
		// to hub - test fails saying hub does not have node!!
		// very rare
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
	}

	@BeforeTest
	public void setUp() throws MalformedURLException {

		DesiredCapabilities dc = DesiredCapabilities.chrome();

		if (System.getProperty("browser").equals("firefox"))
			dc = DesiredCapabilities.firefox();

		String host = System.getProperty("seleniumHubHost");
		System.out.println(host);
		System.out.println("Hua Test git hub webhook");

		driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
		// driver = new RemoteWebDriver(new URL("http://localhost:9515"), dc);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}
