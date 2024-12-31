package com.agencyportal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//a[text()='Edit your account information']")
	private WebElement editYourAccountInformationOption;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Actions
	public boolean getDispalyStatusOfEditYourAccountInformation() {
		boolean displayStatus=editYourAccountInformationOption.isDisplayed();
		return displayStatus;
	}
}
