package hcl.Hybrid.Base;

import java.util.Properties;

public class Config extends TestBase{
	
	private Properties or;
	
	public Config(Properties or){
		this.or = or;
	}
	public String getUserName() {
		return or.getProperty("Username");
	}

	public String getPassword() {
		return or.getProperty("Password");
	}

	public String getWebsite() {
		return or.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(or.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(or.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		//System.out.println(or.getProperty("ExplicitWait"));;
		return Integer.parseInt(or.getProperty("ExplicitWait"));
	}

	public String getDbType() {
		return or.getProperty("DataBase.Type");
	}

	public String getDbConnStr() {
		return or.getProperty("DtaBase.ConnectionStr");
	}
	public String getBrowser() {
		System.out.println("getBrowser   BNNNNN");
		return or.getProperty("Browser");
		
	}

}
