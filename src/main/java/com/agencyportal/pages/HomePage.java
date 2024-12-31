package com.agencyportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	//Objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropDown;

	@FindBy(linkText="Login")
	private WebElement loginOption;

	@FindBy(linkText="Register")
	private WebElement registerOption;

	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;

	@FindBy(name="search")
	private WebElement searchBoxField;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, HomePage.this);	
	}

	//Actions
//	public void clickOnMyAccount() {
//		myAccountDropDown.click();
//	}
//	public LoginPage selectLoginOption() {
//		loginOption.click();
//		return new LoginPage(driver);
//	}
	public void enterSearchProduct(String productText) {
		searchBoxField.sendKeys(productText);
	}
//	public RegisterPage selectRegisterOption() {
//		registerOption.click();
//		return new RegisterPage(driver);
//	}
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		myAccountDropDown.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	public RegisterPage navigateToRegisterPage() {
		myAccountDropDown.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
}