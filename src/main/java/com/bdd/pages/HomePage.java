package com.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdd.utilities.ElementHelper;




public class HomePage {

	private WebDriver driver;
	private ElementHelper elementHelper;

	protected static Logger log = LoggerFactory.getLogger(HomePage.class);
	
	@FindBy(id = "argos-logo")
	private WebElement argosHomeIcon;

	/**
	 * Constructor to assign driver.
	 *
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementHelper = new ElementHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyDirectedToHomePage() {

		elementHelper.waitForElementVisible(argosHomeIcon);
		elementHelper.isElementPresent(argosHomeIcon);
		log.info("User is directed to Home page");
	}

}
