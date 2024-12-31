package com.agencyportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSucessPage {
	
	WebDriver driver;

	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement accountSuccessPageMessage;
	
	public AccountSucessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, AccountSucessPage.this);
	}

	public String retriveAccountSuccessPageText() {
		String accountSuccessMessage = accountSuccessPageMessage.getText();
		return accountSuccessMessage;
	}
}
