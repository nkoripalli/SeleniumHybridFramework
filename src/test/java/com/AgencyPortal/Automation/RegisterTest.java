package com.AgencyPortal.Automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.agencyportal.pages.AccountSucessPage;
import com.agencyportal.pages.HomePage;
import com.agencyportal.pages.RegisterPage;
import com.agencyportal.utils.CommonUtils;

import Base.Base;

public class RegisterTest extends Base {
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSucessPage accountSuccessPage;
	public RegisterTest() {
		super();
	}
		
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();	
	}

	@Test(priority=1)
	public void RegisteringAnAccountWithMandatoryFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(CommonUtils.getEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		String actaulSuccessHeading = accountSuccessPage.retriveAccountSuccessPageText();
		Assert.assertEquals(actaulSuccessHeading, dataProp.getProperty("accountSuccessfullyCreated"),"Account Success message is not displayed");
	}

	@Test(priority=2)
	public void RegisteringAnAccountWithAllFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(CommonUtils.getEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
		String actaulSuccessHeading = accountSuccessPage.retriveAccountSuccessPageText();
		Assert.assertEquals(actaulSuccessHeading, dataProp.getProperty("accountSuccessfullyCreated"),"Account Success message is not displayed");
	}
	
	@Test(priority=3)
	public void RegisteringAnAccountWithExistingEmailAddress() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String actaulWarning = registerPage.retriveDuplicateEmailWarningMessage();
		Assert.assertTrue(actaulWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate email is not displayed");
	}

	@Test(priority=4)
	public void RegisteringAnAccountWithoutFillingAnyDetails() {
		registerPage.clickOnContinueButton();

		String actaulPolicyPolicyWarning = registerPage.retrivePrivacyPolicyWarning();
		Assert.assertTrue(actaulPolicyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy warning message regarding duplicate email is not displayed");
		
		String actualFirstNameWarning = registerPage.retriveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"), "FirstName warning message is not displayed");

		String actualLastNameWarning = registerPage.retriveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"), "LastName warning message is not displayed");
		
		String actualEmailWarning = registerPage.retriveEmailWarning();
		Assert.assertEquals(actualEmailWarning,dataProp.getProperty("emailWarning"), "Eamil warning is not displayed");

		String actualTelephoneWarning = registerPage.retriveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"), "Telephone warning is not displayed");

		String actualPasswordWarning = registerPage.retrivePasswordWarning();
		Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("passwordWarning"), "Password warning is not displayed");
	}
		
}
