package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage  extends BasePage
	{
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//span[text()='My Account']")WebElement lnkMyaccount;
	@FindBy(linkText="Register")WebElement lnkRegister;
	@FindBy(linkText="Login")WebElement lnkLogin;
	
	// Action Methods
	public void clickMyAccount() 
	{
		lnkMyaccount.click();
	}
	public void clickRegister()
	{
		lnkRegister.click();
	}
	public void clickLogin()
	{
		lnkLogin.click();
	}
	}
