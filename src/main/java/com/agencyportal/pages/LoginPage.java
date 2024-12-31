package com.agencyportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	//Objects
	@FindBy(id="input-email")
	private WebElement emailAddressField;

	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement emailPasswordNotMatchWarningMessage;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, LoginPage.this);
	}
	
	//Actions
	public AccountPage Login(String emailText,String passwordText) {
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
	}
//	public void enterPassword(String passwordText) {
//		passwordField.sendKeys(passwordText);
//	}
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retriveEmailPasswordNotMatchWarningMessageText() {
		String warningText= emailPasswordNotMatchWarningMessage.getText();
		return warningText;
	}
}
