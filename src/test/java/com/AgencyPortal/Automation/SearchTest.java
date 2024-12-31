package com.AgencyPortal.Automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agencyportal.pages.HomePage;
import com.agencyportal.pages.SearchPage;

import Base.Base;

public class SearchTest extends Base{

	public WebDriver driver;
	SearchPage searchPage;
	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();	
	}

	@Test(priority=1)
	public void searchForValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterSearchProduct(dataProp.getProperty("validProduct"));
		searchPage=homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.displayStatusOfHPProduct(),"Valid product is not displayed");
	}
	
	@Test(priority=2)
	public void searchForInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterSearchProduct(dataProp.getProperty("inValidProduct"));
		searchPage= homePage.clickOnSearchButton();
		String actualSeacrgMessage = searchPage.retriveNoProductMessage();
		Assert.assertEquals(actualSeacrgMessage, dataProp.getProperty("noProductTextInSearchResults"), "Message is not displayed please check");
	}
	
	@Test(priority=3,dependsOnMethods= {"searchForValidProduct","searchForInvalidProduct"})
	public void searchWithoutAnyProduct() {
		HomePage homePage = new HomePage(driver);
		searchPage=homePage.clickOnSearchButton();
		String actualSeacrgMessage = searchPage.retriveNoProductMessage();
		Assert.assertEquals(actualSeacrgMessage, dataProp.getProperty("noProductTextInSearchResults"), "Message is not displayed please check");
	}
}
