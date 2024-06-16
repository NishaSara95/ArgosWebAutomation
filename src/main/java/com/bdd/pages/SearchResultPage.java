package com.bdd.pages;


import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdd.utilities.ElementHelper;

import lombok.extern.log4j.Log4j2;


public class SearchResultPage {

	private WebDriver driver;
	private ElementHelper elementHelper;
	protected static Logger log = LoggerFactory.getLogger(SearchResultPage.class);

	@FindBy(id = "searchTerm")
	private WebElement searchBox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchBtn;

	@FindBy(xpath = "//div[@data-test='component-product-card-title']")
	private List<WebElement> productCards;

	/**
	 * Constructor to assign driver.
	 *
	 * @param driver
	 */
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementHelper = new ElementHelper(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to enter product in search box text field
	 * 
	 * @param product name
	 */
	public void enterProductInSearchBox(String productname) {

		elementHelper.enterData(searchBox, productname);
		log.info("Product is entered in the search box");

	}

	/**
	 * Method to click on search button
	 */
	public void clickSearchBtn() {

		elementHelper.explicitWaitForClickable(searchBtn);
		elementHelper.click("Search Button", searchBtn);
		log.info("Search button is clicked");
	}

	/**
	 * Method to verify search results contain only the specified product category
	 * 
	 * @param productCategory
	 * @return true only if all product titles contain the productCategory
	 */
	public boolean verifySearchResultsContainOnly(String productCategory) {
		elementHelper.waitForPageToLoad();	
		elementHelper.waitForElementsVisible(productCards);
		for (WebElement title : productCards) {
			if (!title.getText().toLowerCase().contains(productCategory.toLowerCase())) {
				log.info("Search results contains other products than the specified product category");
				return false;
			}
		}
		log.info("Search results contain only the specified product category");
		return true;
	}
	
	

	/**
	 * Method to click on a random product from Product list
	 * 
	 */

	public void selectRandomProduct() {

		elementHelper.waitForElementsVisible(productCards);
		Random random = new Random();
		int index = random.nextInt(productCards.size());
		WebElement randomProduct = productCards.get(index);
		elementHelper.click("Random Product", randomProduct);
		elementHelper.waitForPageToLoad();
		log.info("Product is selected from the search page");
	}
	
	/** 
	 * Select a specific product from the list 
	 * 
	 * @param product
	 */
	 
	
	public void selectSpecificProduct(String product) {
		elementHelper.waitForElementsVisible(productCards);
		for (WebElement prod : productCards) {
			if (prod.getText().toLowerCase().contains(product)) {
				prod.click();
				log.info("Product selected from search page is :  " +product);				
				break;
			}
		}
		
	}
}
