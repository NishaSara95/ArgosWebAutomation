package com.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bdd.utilities.ElementHelper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HomePage {

	private WebDriver driver;
	private ElementHelper elementHelper;

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
