package com.bdd.utilities;

import io.cucumber.java.Scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverManager {
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	private WebDriverManager() {
		throw new IllegalStateException("WebDriverManager class cannot be instantiated.");
	}

	public static WebDriver getDriver() {
		WebDriver driver = driverThreadLocal.get();
		if (driver == null) {
	            String browserName = CommonUtils.getProperty("browser").toLowerCase(); 
				switch (browserName) {
				case "chrome":
					driver = createChromeDriver();
					break;
				case "edge":
					driver = createEdgeDriver();
					break;
				default:
					throw new IllegalArgumentException("Unsupported browser: " + browserName);
				}

				driverThreadLocal.set(driver);
				launchURL(driver);
			
		}
		return driver;
	}

	private static WebDriver createChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--disable-popup-blocking");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-save-password-bubble");
		chromeOptions.addArguments("--ignore-certificate-errors");
		chromeOptions.addArguments("--disable-translate");
		chromeOptions.addArguments("--no-default-browser-check");
		chromeOptions.addArguments("--incognito");

		io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
		return new ChromeDriver(chromeOptions);
	}



	private static WebDriver createEdgeDriver() {
		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.setCapability("acceptInsecureCerts", true);

		io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
		return new EdgeDriver(edgeOptions);
	}

	public static void launchURL(WebDriver driver) {
		System.out.println("####################################");
		System.out.println(CommonUtils.getProperty("argos_url"));
		System.out.println("####################################");
		driver.get(CommonUtils.getProperty("argos_url"));
		driver.manage().window().maximize();
		WebElement cookieAccept = driver.findElement(By.id("explicit-consent-prompt-accept"));

		WebDriverWait wait = new WebDriverWait(driver, 50);

		wait.until(ExpectedConditions.visibilityOf(cookieAccept));

		wait.until(ExpectedConditions.elementToBeClickable(cookieAccept));
		cookieAccept.click();
	}

	public static void quitDriver(Scenario scenario) {
		WebDriver driver = driverThreadLocal.get();
		try {
			if (scenario.isFailed()) {

				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the WebDriver instance
			if (driver != null) {
				driver.quit();
				driverThreadLocal.remove();
			}
		}
	}
}
