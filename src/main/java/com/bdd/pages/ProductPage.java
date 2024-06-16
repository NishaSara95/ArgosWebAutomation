package com.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdd.utilities.ElementHelper;

public class ProductPage {

	private WebDriver driver;
	private ElementHelper elementHelper;

	protected static Logger log = LoggerFactory.getLogger(ProductPage.class);
	
	@FindBy(xpath = "//button[@data-test='add-to-trolley-button-button']")
	private WebElement addToTrolleyBtn;

	@FindBy(xpath = "//span[@data-test='product-title']")
	private WebElement productName;

	@FindBy(xpath = "//button[@data-test='component-att-modal-button-close']")
	private WebElement closeBtn;

	@FindBy(xpath = "//h4[@id='component-att-modal-drawer-title']/strong")
	private WebElement addedToTrolleyTitle;

	@FindBy(xpath = "//a[@data-test='trolley']")
	private WebElement trolleyBtn;

	@FindBy(xpath = "//span[@data-e2e='product-line-price']")
	private WebElement productPrice;

	/**
	 * Constructor to assign driver.
	 *
	 * @param driver
	 */
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		elementHelper = new ElementHelper(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to get the product title that is getting added to trolley
	 * 
	 * @return
	 */

	public String getProductTitle() {
		elementHelper.waitForPageToLoad();
		elementHelper.waitForElementVisible(productName);
		return productName.getText();

	}

	/**
	 * Method to click on Add to trolley
	 * 
	 */

	public void clickAddToTrolley() {

		elementHelper.waitForElementVisible(addToTrolleyBtn);
		elementHelper.scrollIntoView(addToTrolleyBtn);
		elementHelper.click("Add to Trolley Btn", addToTrolleyBtn);
		log.info("Add to trolley button is clicked");
	}

	/**
	 * Method to handle Added to Trolley dialog box
	 * 
	 */
	public void closeAddTrolleyDialogBox() {

		elementHelper.waitForElementClickable(closeBtn);
		elementHelper.click("AddTrolley Close Btn", closeBtn);
		log.info("Add to trolley close button is clicked");
	}

	/**
	 * Method to click on Trolley
	 * 
	 */
	public void clickTrolleyButton() {
		elementHelper.scrollToTop();
		WebElement trolleyButton = driver.findElement(By.xpath("//a[@data-test='trolley']"));
		elementHelper.click("Trolley Btn", trolleyButton);
		log.info("Trolley button is clicked");
		elementHelper.waitForPageToLoad();

	}

	/**
	 * Method to return the product price
	 * 
	 * @return
	 */
	public String getProductPrice() {
		elementHelper.waitForElementVisible(productPrice);
		return productPrice.getText();

	}

}
