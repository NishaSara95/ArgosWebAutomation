package com.bdd.pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdd.utilities.ElementHelper;



public class TrolleyPage {

	private WebDriver driver;
	private ElementHelper elementHelper;
	protected static Logger log = LoggerFactory.getLogger(TrolleyPage.class);


	@FindBy(xpath = "(//a[@data-e2e='product-name'])[2]/span")
	private WebElement trolleyproductName;

	@FindBy(xpath = "//select[@data-e2e='product-quantity']")
	private WebElement quantityDd;

	@FindBy(xpath = "//div[@data-e2e='basket-total-price']")
	private WebElement subTotal;

	@FindBy(xpath = "//span[@data-e2e='product-line-price']")
	private WebElement productPrice;

	@FindBy(xpath = "//select[@data-e2e='product-quantity']/option")
	private List<WebElement> prodQuantityDd;

	/**
	 * Constructor to assign driver.
	 *
	 * @param driver
	 */
	public TrolleyPage(WebDriver driver) {
		this.driver = driver;
		elementHelper = new ElementHelper(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to get the product name from Trolley
	 * 
	 * @return
	 */

	public String getProductNameFromTrolley() {
		elementHelper.waitForPageToLoad();
		elementHelper.waitForElementVisible(trolleyproductName);
		return trolleyproductName.getText();
	}

	/**
	 * Method to select the quantity value from dropdown
	 * 
	 * @param quantity
	 */
	public double getSingleProductPrice() {
		elementHelper.waitForElementVisible(productPrice);
		String singleUnitPriceText = productPrice.getText().replaceAll("[^0-9.]", "");
		double singleUnitPrice = Double.parseDouble(singleUnitPriceText);
		return singleUnitPrice;

	}

	public double selectQuantityDropdownAndGetTotal(String quantity) {
		double singleProductPrice = getSingleProductPrice();
		elementHelper.waitForElementVisible(quantityDd);
		elementHelper.click("Quantity Dropdown", quantityDd);
		elementHelper.waitForElementsVisible(prodQuantityDd);
		for (WebElement quan : prodQuantityDd) {
			if (quan.getText().equals(quantity)) {
				elementHelper.click("Quantity", quan);
				log.info("Selected quantity: " + quantity);
				elementHelper.waitForPageToLoad();
				break;
			}
		}
		int quantityInt = Integer.parseInt(quantity);
		log.info("Single unit * quantity:  " + singleProductPrice * quantityInt);
		return singleProductPrice * quantityInt;

	}

	/**
	 * Method to retrieve the sub total price
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public double getSubTotalPrice() throws InterruptedException {
		elementHelper.waitForElementVisible(subTotal);
		String subtotalText = subTotal.getText().replaceAll("[^0-9.]", "");
		double subtotal = Double.parseDouble(subtotalText);
		log.info("Subtotal is :"  +subtotal);
		return subtotal;
	}

}
