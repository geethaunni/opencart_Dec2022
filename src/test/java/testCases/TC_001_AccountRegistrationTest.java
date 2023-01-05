package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
	{
	
	@Test(groups = {"Regression","Master"})
	public void test_account_Registration()
	{
		logger.info("*****starting TC_001_AccountRegistrationTest*****");
		logger.trace("capturing tracing logs");
		logger.debug("capturing debug logs");
		try
		{
		HomePage hp= new HomePage(driver);
		logger.info("clicking on my account link");
		hp.clickMyAccount();
		logger.info("clicking on registration link");
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);	
		
		logger.info("*providing customer details");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName((randomString().toUpperCase()));
		regpage.setEmail(randomString()+"@gmail.com"); // it will return only randomstring only. not valid email id. this will accept only email format
		regpage.setTelephone(randomNumber());
		
		String password = randomalphanumeric(); // actual password and confirm password should be same. thats why created this password and passing the same
		regpage.setPassword(password);
		regpage.setConfirmPassword(password); // wrong password
		regpage.setPrivacyPolicy();
		
		logger.info("clicking on cotinue button");
		regpage.clickContinue();
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("verifying customer registraion");
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{			
			logger.info("test passed......");
			Assert.assertTrue(true);
	//Assert.assertEquals(confmsg, "Your Account Has Been Created!");	//confirmation msg need to validate
		}
		else
		
			{
				logger.warn("customer registration is not successful");
				logger.error("test failed.....");
				Assert.assertTrue(false);
				
			}
		}
		catch(Exception e)
		{
			//logger.warn("customer registration is not successful");
			//logger.error("customer registraion failed");
			Assert.fail();
		}
		logger.info("*****finished TC_001_AccountRegistrationTest*****");
	}
	
		}




