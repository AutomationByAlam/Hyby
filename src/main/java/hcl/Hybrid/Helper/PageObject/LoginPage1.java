package hcl.Hybrid.Helper.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import hcl.Hybrid.Base.Config;
import hcl.Hybrid.Base.TestBase;
import hcl.Hybrid.Helper.AssertionHelp.VerificationHelper;
import hcl.Hybrid.Helper.DropDown.DropDownHelper;
import hcl.Hybrid.Helper.Logger.LoggerHelper;
import hcl.Hybrid.Helper.Wait.WaitHelper;

public class LoginPage1 extends TestBase{
	

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage1.class);
	WaitHelper waitHelper;
	DropDownHelper dropSel;
	
	@FindBy(xpath=".//*[@id='txtUserID']")
	WebElement username;
	

	@FindBy(xpath=".//*[@id='txtPassword']")
	WebElement password;
	

	@FindBy(xpath=".//*[@id='ddlDomain']")
	WebElement domain;
	

	@FindBy(xpath=".//*[@id='btnSubmit']")
	WebElement login;
	

	@FindBy(xpath=".//*[@id='lblMsg']")
	WebElement authfail;
	

	@FindBy(linkText="Forgot/Reset Password")
	WebElement forPass;
	

	@FindBy(linkText="Login Help")
	WebElement loginHelp;
	
public LoginPage1(WebDriver driver)
{
this.driver=driver;
PageFactory.initElements(driver, this);
waitHelper=new WaitHelper(driver);

System.out.println("");
Config cnf = new Config(TestBase.or);
int time = cnf.getExplicitWait();
waitHelper.waitForElement(driver,username,time);
}

public void loginToApplication(String uname,String pass)
{
log.info("Entering Username : "+uname.toString());	
username.sendKeys(uname);	
log.info("Entering password : "+pass.toString());	
password.sendKeys(pass);
dropSel = new DropDownHelper(driver);
dropSel.SelectUsingVisibleText(domain,"GEO");
log.info("selectED dropdown");
login.click();
VerificationHelper.verifyTextEquals(authfail,"Either your login credentials are not correct or Login ID does not exist in selected domain.");

}




public String getInvalidText()
{
	
	//waitForElement(driver,300,authfail);
	log.info("error message : "+authfail.getText());	
	return authfail.getText();
}

public void submitMainPage()
{
	login.click();
	//return new MainPage(driver);
}


}




