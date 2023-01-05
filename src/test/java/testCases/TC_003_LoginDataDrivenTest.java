package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
		// modification
public class TC_003_LoginDataDrivenTest extends BaseClass
{
	@Test (dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= {"datadriven"})
	public void test_loginDDT(String email, String pwd, String exp)
	{
		logger.info("*****Starting TC_003_LoginDataDrivenTest*****");
		try {
		//HomePage --> MyAccount --->Login
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//loginPage --->  provide email & password then click
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//check MyAccount page is present or not - for that create an object
		
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
		
		if(exp.equals("Valid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}			
		}		
		
		if(exp.equals("Invalid")) 
		{
			if(targetpage==true)
			{	
				
				//MyAccountPage myaccpage = new MyAccountPage(driver);
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}		
		
		logger.info("****finished TC_003_LoginDataDrivenTest**** ");
	}
}


