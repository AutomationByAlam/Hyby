package hcl.Hybrid.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import hcl.Hybrid.Helper.Wait.WaitHelper;
import hcl.Hybrid.excelReader.Excel_reader;



public class TestBase {
	
	public static final Logger logger = Logger.getLogger(TestBase.class.getClass());
	
	public WebDriver driver;
	public static Properties or;
	File fi;
	FileInputStream fis;
	
	
	public void getBrowser(String browser)
	{
		
		if(System.getProperty("os.name").contains("Windows"))
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			   driver = new FirefoxDriver();
		
			
			}
			
			else if(browser.equalsIgnoreCase("chrome"))
			{
				
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			    driver = new ChromeDriver();
			}
			
		}
	}
	
	public void loadPropertiesFile() throws IOException
	{
		
	String log4jpath = "log4j.properties";
	PropertyConfigurator.configure(log4jpath);
	logger.info("Loading log4j properties");
	
		
	or = new Properties();
	fi = new File(System.getProperty("user.dir")+"/src/main/java/hcl/Hybrid/Config/config.properties");
	fis = new FileInputStream(fi);
	or.load(fis);
	logger.info("Loading config properties");
	

	
	fi = new File(System.getProperty("user.dir")+"/src/main/java/hcl/Hybrid/Config/Login.properties");
	fis = new FileInputStream(fi);
	or.load(fis);
	logger.info("Loading Login properties");
	
	
		
	}
	
	
	public WebElement getLocator(String locator) throws Exception {
		//System.out.println(locator);
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println("locatorType:-"+locatorType);
		System.out.println("locatorValue:-"+locatorValue);
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public  List<WebElement> getLocators(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println("locatorType:-"+locatorType);
		System.out.println("locatorValue:-"+locatorValue);

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	

	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementWithPollingInterval(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void impliciteWait(long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
public String getScreenShot(String imageName) throws IOException{
		
		if(imageName.equals("")){
			imageName = "blank";
		}
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")+"/src/main/java/hcl/Hybrid/ScreenShot/";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
	return actualImageName;
	}

public void getresult(ITestResult result) throws IOException {
	if (result.getStatus() == ITestResult.SUCCESS) {
		String screen = getScreenShot("PASS");
	//	test.log(LogStatus.PASS, result.getName() + " test is pass");
	} else if (result.getStatus() == ITestResult.SKIP) {
		//test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
	} else if (result.getStatus() == ITestResult.FAILURE) {
		//test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
		String screen = getScreenShot("");
		//test.log(LogStatus.FAIL, test.addScreenCapture(screen));
	} else if (result.getStatus() == ITestResult.STARTED) {
		//test.log(LogStatus.INFO, result.getName() + " test is started");
	}
}

@AfterMethod()
public void afterMethod(ITestResult result) throws IOException {
	getresult(result);
}

@BeforeMethod()
public void beforeMethod(Method result) {
	//test = extent.startTest(result.getName());
	//test.log(LogStatus.INFO, result.getName() + " test Started");
}

@AfterClass(alwaysRun = true)
public void endTest() {
	driver.quit();
	//extent.endTest(test);
	//extent.flush();
}

public WebElement getWebElement(String locator) throws Exception{
	return getLocator(or.getProperty(locator));
}

public  String[][] getData(String excelName,String sheetName)
{
	String excellocation = System.getProperty("user.dir")+"/src/main/java/hcl/Hybrid/Data/"+excelName;
	Excel_reader excel = new Excel_reader();
	System.out.println("excellocation  "+System.getProperty("user.dir"));
 return	excel.getExcelData(excellocation, sheetName);	
}


@BeforeTest
public void launchBrowser(){
	try {
		loadPropertiesFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	Config config = new Config(or);
	getBrowser(config.getBrowser());
	WaitHelper waitHelper = new WaitHelper(driver);
	waitHelper.setImplicitWait(config.getImplicitWait(), TimeUnit.SECONDS);
	waitHelper.setPageLoadTimeout(config.getPageLoadTimeOut(), TimeUnit.SECONDS);
}


public  void test()
{
	
System.out.println("Before1 Config");
	
	Config config = new Config(or);
	//System.out.println("after Config "+config.getBrowser());
	config.getBrowser();
}
	
	public static void main(String[] args) throws Exception {
		TestBase test = new TestBase();
		test.loadPropertiesFile();
		//test.getBrowser("chrome");
		test.test();
		
		
		System.out.println(test.or.getProperty("Username"));
		System.out.println(test.or.getProperty("email"));
	//	test.getScreenShot("");
		
		//test.getLocator("email");
		
		
		//test.getWebElement("submitbutton");
		//test.getWebElements("submitbutton");
		
	//test.getWebElement("email");
//	String[][] data = test.getData("TestData.xlsx", "LoginTestData");
//	System.out.println(data);

	}


	
}
