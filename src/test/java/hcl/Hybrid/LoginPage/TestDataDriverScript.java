package hcl.Hybrid.LoginPage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hcl.Hybrid.Base.TestBase;



public class TestDataDriverScript extends TestBase{
	
	@DataProvider(name="testData")
	public  Object[][] dataSource(){
		return getData("TestData.xlsx", "LoginTestData");
	}
	
	@Test(dataProvider="testData")
	public void testLogin(String userName, String password, String runmode){
		System.out.println("userName:-"+userName);
		System.out.println("password:-"+password);
		System.out.println("runmode:-"+runmode);
		//driver.findElement(By.xpath("")).sendKeys(userName);
		//driver.findElement(By.xpath("")).sendKeys(password);
		//driver.findElement(By.xpath("")).sendKeys(runmode);
	}
	
	@DataProvider(name="testData1")
	public Object[][] dataSource1(){
		return getData("TestData.xlsx", "Registration");
	}
	
	@Test(dataProvider="testData1")
	public void testLogin1(String userName, String password, String runmode,String az,String az1,String az3){
		System.out.println("userName:-"+userName);
		System.out.println("password:-"+password);
		System.out.println("runmode:-"+runmode);
		//driver.findElement(By.xpath("")).sendKeys(userName);
		//driver.findElement(By.xpath("")).sendKeys(password);
		//driver.findElement(By.xpath("")).sendKeys(runmode);
	}

	
	}
