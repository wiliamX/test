package cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.*;

import test.TxtHelper;
import test.XmlHelper;

public class Login {
	private static String baseUrl;
	private static WebDriver driver;
	
	@BeforeMethod
	public void setUp()throws Exception{
		System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		ProfilesIni pi = new ProfilesIni();
		FirefoxProfile profile = pi.getProfile("default");
		driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		baseUrl = "http://www.nong12.com/";
		driver.get(baseUrl);
	}

	@Test
	public void loginT()throws Exception{
		String login = XmlHelper.parserXml("/test/login/input/LoginLnk", "value");
		driver.findElement(By.xpath(login)).click();
		//get Xpath
		String userName = XmlHelper.parserXml("/test/login/input/userName", "value");
		String passWord = XmlHelper.parserXml("/test/login/input/passWord", "value");
		String loginBtn = XmlHelper.parserXml("/test/login/input/loginBtn", "value");
		
		//get testData
		String userNameValue = TxtHelper.getData("logint", 0);
		String passWordValue = TxtHelper.getData("logint", 1);
		
		//login
		driver.findElement(By.xpath(userName)).sendKeys(userNameValue);
		driver.findElement(By.xpath(passWord)).sendKeys(passWordValue);
		driver.findElement(By.xpath(loginBtn)).click();
		
		//assert
		String assertValue = TxtHelper.getData("logint", 2);
		String nameErr = XmlHelper.parserXml("/test/login/assert/nameErr", "value");
		String asserTextValue = driver.findElement(By.xpath(nameErr)).getText();

		Assert.assertEquals(asserTextValue, assertValue);
	
	}
	
	@Test
	public void login()throws Exception{
		String login = XmlHelper.parserXml("/test/login/input/LoginLnk", "value");
		driver.findElement(By.xpath(login)).click();
		//get Xpath
		String userName = XmlHelper.parserXml("/test/login/input/userName", "value");
		String passWord = XmlHelper.parserXml("/test/login/input/passWord", "value");
		String loginBtn = XmlHelper.parserXml("/test/login/input/loginBtn", "value");
		
		//get testData
		String userNameValue = TxtHelper.getData("login", 0);
		String passWordValue = TxtHelper.getData("login", 1);
		
		//login
		driver.findElement(By.xpath(userName)).sendKeys(userNameValue);
		driver.findElement(By.xpath(passWord)).sendKeys(passWordValue);
		driver.findElement(By.xpath(loginBtn)).click();
		
		//assert
		String assertValue = TxtHelper.getData("login", 2);
		String nameErr = XmlHelper.parserXml("/test/login/assert/nameErr", "value");
		String asserTextValue = driver.findElement(By.xpath(nameErr)).getText();

		Assert.assertEquals(asserTextValue, assertValue);
	
	}
	
	@AfterMethod
	public void tearDown()throws Exception{
		driver.quit();	
	}	
}

