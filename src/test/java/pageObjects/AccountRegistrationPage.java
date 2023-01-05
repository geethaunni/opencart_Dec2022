package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage

// page object class is only meant for defining all the page elements and corresponding action methods. 
// Page object class is not for validation. Validations always in the TestNG test method.
	{
	public AccountRegistrationPage(WebDriver driver)
	{	
		super(driver);
	}
	//Locators
	@FindBy(name="firstname")WebElement txtFirstname;
	@FindBy(name="lastname")WebElement txtLastname;
	@FindBy(name="email")WebElement txtEmail;
	@FindBy(name="telephone")WebElement txtTelephone;
	@FindBy(name="password")WebElement txtPassword;
	@FindBy(name="confirm")WebElement txtConfirmPassword;
	@FindBy(name="agree")WebElement chkPolicy;	
	@FindBy(xpath="//input[@value='Continue']")WebElement btnContinue;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")WebElement msgConfirmation;
	
	//Action Methods
	public void setFirstName(String fname) 
	{
		txtFirstname.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String tel) 
	{
		txtTelephone.sendKeys(tel);
	}
	public void setPassword(String pwd) 
	{
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPassword(String pwd) 
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	public void setPrivacyPolicy() 
	{
		chkPolicy.click();
	}
	public void clickContinue() 
	{
		btnContinue.click();	
	}
	public String getConfirmationMsg() 
	{
		try 
	{
		return (msgConfirmation.getText());
	}	catch (Exception e) 
	{
		return (e.getMessage());

	}

		
	}
	}
