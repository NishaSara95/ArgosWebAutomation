package com.bdd.stepdefinitions;

import com.bdd.pages.HomePage;
import com.bdd.pages.ProductPage;

import com.bdd.pages.SearchResultPage;
import com.bdd.pages.TrolleyPage;
import com.bdd.utilities.ElementHelper;

import com.bdd.utilities.WebDriverManager;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Log4j2
public class ArgosShoppingStepDefinition {

	private Scenario scenario;
	private WebDriver driver;
	private ElementHelper elementHelper;
	private HomePage homePage;
	private SearchResultPage searchPage;
	private ProductPage productPage;
	private TrolleyPage trolleyPage;
	String productTitle;

	@Before
	public void setUp(Scenario scenario) {
		driver = WebDriverManager.getDriver();
		homePage = new HomePage(driver);
		elementHelper = new ElementHelper(driver);
		searchPage = new SearchResultPage(driver);
		trolleyPage = new TrolleyPage(driver);
		productPage= new  ProductPage(driver);
		this.scenario = scenario;
		log.info("Scenario: " + scenario.getName()); // Log scenario name
	}

	@After
	public void quit() {
		WebDriverManager.quitDriver(scenario);
		log.info("WebDriver quit.");
	}

	@Given("User navigates to Argos website")
	public void user_navigates_to_argos_website() {
		homePage.verifyDirectedToHomePage();

	}

	@When("User searches for a product {string}")
	public void user_searches_for_a_product(String productname)  {

		searchPage.enterProductInSearchBox(productname);
		searchPage.clickSearchBtn();
		
	}

	@Then("User verifies the search result page contains {string}")
	public void user_verifies_the_search_result_page_contains(String productCategory) {
		boolean result = searchPage.verifySearchResultsContainOnly(productCategory);
		Assert.assertTrue(result, "Search results do not contain only the specified product category.");

	}

	@Then("User adds the product to the trolley")
	public void user_adds_the_product_to_the_trolley() {

		searchPage.selectRandomProduct();
		productTitle = productPage.getProductTitle();
		productPage.clickAddToTrolley();
		productPage.closeAddTrolleyDialogBox();
		
	}

	@Then("User validates the product is in the trolley")
	public void user_validates_the_product_is_in_the_trolley() {
		productPage.clickTrolleyButton();
		Assert.assertEquals(trolleyPage.getProductNameFromTrolley(), productTitle);
		
		
	}
	
	@And("User increase the quantity by {string}")
	public void user_increase_the_quantity_by(String quant) {
	   trolleyPage.selectQuantityDropdownAndGetTotal(quant);
		
	}
	@Then("User validate the subtotal with unit price and {string}")
	public void user_validate_the_subtotal(String Quantity) throws InterruptedException {
		   Assert.assertEquals(trolleyPage.selectQuantityDropdownAndGetTotal(Quantity),trolleyPage.getSubTotalPrice());
	}


}
