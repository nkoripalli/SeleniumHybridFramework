package com.AgencyPortal.Automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agencyportal.pages.AccountPage;
import com.agencyportal.pages.HomePage;
import com.agencyportal.pages.LoginPage;
import com.agencyportal.utils.CommonUtils;

import Base.Base;

public class LoginTest extends Base{
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		loginPage= homePage.navigateToLoginPage();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();	
	}

	@Test(priority=1, dataProvider="validCredentialsSupplier")
	public void loginWithValidCredentials(String email, String password) {
		AccountPage accountPage = loginPage.Login(email,password);
		Assert.assertTrue(accountPage.getDispalyStatusOfEditYourAccountInformation());
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = CommonUtils.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority=2)
	public void loginWithInvalidCredentails() {
		loginPage.Login(CommonUtils.getEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchWarningMessageText();
		String expectedWarnigMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarnigMessage), "Expected Warming message is not Displayed");	}

	@Test(priority=3)
	public void loginWithInvalidEmailAndValidPassword() {
		loginPage.Login(CommonUtils.getEmailWithTimeStamp(),prop.getProperty("validPassword"));
		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchWarningMessageText();
		String expectedWarnigMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarnigMessage), "Expected Warming message is not Displayed");
	}

	@Test(priority=4)
	public void loginWithValidEmailAndInvalidPassword() {
		loginPage.Login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		String expectedWarnigMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchWarningMessageText().contains(expectedWarnigMessage), "Expected Warming message is not Displayed");	
	}
	
	@Test(priority=5)
	public void loginWithoutProvidingCredentials() {
		loginPage.clickOnLoginButton();
//		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchWarningMessageText();
//		String expectedWarnigMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warming message is not Displayed");	
	}
}
