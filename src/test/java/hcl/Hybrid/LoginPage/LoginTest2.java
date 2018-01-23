package hcl.Hybrid.LoginPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import hcl.Hybrid.Base.Config;
import hcl.Hybrid.Base.TestBase;
import hcl.Hybrid.Helper.Logger.LoggerHelper;
import hcl.Hybrid.Helper.PageObject.LoginPage1;

public class LoginTest2 extends TestBase {
	
	private final Logger log = LoggerHelper.getLogger(LoginTest2.class);
	
	@Test
	public void loginApp() {

		Config cnfg = new Config(or);
		driver.get(cnfg.getWebsite());
		LoginPage1 lg = new LoginPage1(driver);
		lg.loginToApplication(cnfg.getUserName(),cnfg.getPassword());
		
		
		
	}

}
