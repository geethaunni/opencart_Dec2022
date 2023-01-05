package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;   // for logger
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	 public static WebDriver driver;
	 public Logger logger;
	 public ResourceBundle rb;  // ResourceBundle import from java.util package
	 public Properties p;
		
		@BeforeClass (groups= {"Master","Sanity","Regression","datadriven"})
		@Parameters("browser")
		public void setUp(String br) throws IOException
		{
			// loading properties file -Approach 1
			//rb=ResourceBundle.getBundle("config"); //name of properties  file without extension
			
			// loading properties file - Approach 2
			FileReader file = new FileReader(System.getProperty("user.dir")+ "\\src\\test\\resources\\config.properties");
			p= new Properties();
			p.load(file);			
			
			logger=LogManager.getLogger(this.getClass()); // this is code for log4j
			if(br.equals("chrome"))
			{
			driver= new ChromeDriver();
			}
			else if(br.equals("edge"))
			{
			driver = new EdgeDriver();
			}
			else 
			{
				driver = new FirefoxDriver();
			}
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		//	driver.get(rb.getString("appURL"));
			driver.get(p.getProperty("appURL"));
			
			driver.manage().window().maximize();		
		}
		//These are all user define methods which we created to generate for random data at the runtime.
		public String randomString()
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
		}
		public String randomNumber()
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(10); 
			return generatedstring;
			// send keys always accept text or string type of data. So need not to convert into number
		}
		public String randomalphanumeric()
		{
			String str = RandomStringUtils.randomAlphabetic(3); 
			String num = RandomStringUtils.randomAlphanumeric(3);
			
			return str +" @ "+ num;		
		}	
		public String captureScreen(String tname) throws IOException {

			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

			try {
				FileUtils.copyFile(source, new File(destination));
			} catch (Exception e) {
				e.getMessage();
			}
			return destination;

		}
		
		@AfterClass(groups= {"Master","Sanity","Regression","datadriven"})
		public void teardown()
		{
			driver.quit();
		}
		
}
