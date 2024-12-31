package com.agencyportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	//Objects
	WebDriver driver;
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	@FindBy(id="input-password")
	private WebElement passwordField;
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	@FindBy(xpath="//input[@type='submit']")
	private WebElement continueButton;
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption; 
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarning;		
	@FindBy(xpath="//input[@name='firstname']/following-sibling::div")
	private WebElement firstNameWarning;		
	@FindBy(xpath="//input[@name='lastname']/following-sibling::div")
	private WebElement lastNameWarning;		
	@FindBy(xpath="//input[@name='email']/following-sibling::div")
	private WebElement emailWarning;	
	@FindBy(xpath="//input[@name='telephone']/following-sibling::div")
	private WebElement telephoneWarning;	
	@FindBy(xpath="//input[@name='password']/following-sibling::div")
	private WebElement passwordWarning;		
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Action
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	public void enterlastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}
	public void enterTelephoneNumber(String telephoneNumber) {
		telephoneField.sendKeys(telephoneNumber);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	public void selectNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	public AccountSucessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSucessPage(driver);
	}
	
	public String retriveDuplicateEmailWarningMessage() {
		String duplicateEmailWarningText=  duplicateEmailWarningMessage.getText();
		return duplicateEmailWarningText;
	}
	public String retrivePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public String retriveFirstNameWarning() {
		String firstNameWarningWarningText = firstNameWarning.getText();
		return firstNameWarningWarningText;
	}
	
	public String retriveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retriveEmailWarning() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	public String retriveTelephoneWarning() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}	
	public String retrivePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}		
}
