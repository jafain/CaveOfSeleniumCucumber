package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class steps extends BaseClass {
	
	//Run tests on desired browser
	@Before
	public void setup() throws IOException 
	{
		//logger
		logger=Logger.getLogger("CaveOfSeleniumCucumber"); //Added logger
		PropertyConfigurator.configure("log4j.properties"); //Added logger
		
		//Reading properties
		configProp= new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
	
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe"); //we need change due to desire browser
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome")) 
		{
		System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));

		//Here we need to instantiate driver and lp and pass driver as a parameter for lp
		driver=new ChromeDriver();
		}
		
		
		else if(br.equals("firefox")) 
		{
		System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));

		//Here we need to instantiate driver and lp and pass driver as a parameter for lp
		driver=new FirefoxDriver();
		}
		
		else if(br.equals("ie")) 
		{
		System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));

		//Here we need to instantiate driver and lp and pass driver as a parameter for lp
		driver=new InternetExplorerDriver();
		}
		
		logger.info("******** Luanching browser********");
		
	}
	
	
	
	
	//Create Webdriver (This two variables we create here we use through all methods here and LoginPage.java)
	//public WebDriver driver; //Due to this will used in all classes and we move it to base class 

	//Create LoginPage lp to be able to access all page objects package
	//public LoginPage lp; //Due to this will used in all classes and we move it to base class 

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		
		
		
/*		//Nedanstående flyttat till ovanstående metoden setup() efter att vi ska använda Run tests on desired browser
		logger=Logger.getLogger("CaveOfSeleniumCucumber"); //Added logger
		PropertyConfigurator.configure("log4j.properties"); //Added logger
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers/chromedriver.exe");

		//Here we need to instantiate driver and lp and pass driver as a parameter for lp
		driver=new ChromeDriver();
		logger.info("******** Luanching browser********");
		
*/		
		lp=new LoginPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("******** Opening URL********");
		driver.get(url);
		driver.manage().window().maximize();

	}

	@And("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("******** Providing login details********");
		lp.setUserName(email);
		lp.setPassword(password);

	}

	@And("Click on Login")
	public void click_on_Login() throws InterruptedException {
		logger.info("******** Started login process********");
		lp.clickLogin();
		Thread.sleep(3000);

	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws InterruptedException {
		//This method is check first if user login with incorrect password and will 
		//get unsuccessful otherwise will get correct title 
		//Which is Dashboard / nopCommerce administration specified in feature file
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("******** Login passed********");
			Assert.assertTrue(false);
			//Assert.assertTrue("Login was unsuccessful.", false);
		} else {
			logger.info("******** Login failed********");
			Assert.assertEquals(title, driver.getTitle());
		}

		Thread.sleep(3000);

	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException{
		logger.info("******** Click on logout link********");

		lp.clickLogout();

		Thread.sleep(3000);
	}

	@And("close browser")
	public void close_browser() {
		logger.info("******** Closing browser********");
		driver.quit();


	}


	//Customer feature step definitions start here.........................................

	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		//addCust we created will interact with all methods
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	//The title will called/fetched from getPageTitle method which we created in AddCuctomerPage 

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();

	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomerMenuItem();

	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		addCust.clickOnAddnew();
		Thread.sleep(3000);

	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		//This method is for validation part of script
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("********Adding new customer********");
		logger.info("********Providing customer details********");
		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		//Registred - default
		//The customer cannot be in both 'gusts and registrated' cutomer role
		//Add the customer to 'Guest or 'Registred' customer role

		addCust.setLnkCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Jeffry");
		addCust.setLastName("Svahn");
		addCust.setDob("11/11/1977");
		addCust.setCompanyName("Test lab");
		addCust.setAdminContent("For testing purpose and learning Cucumber");


	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("********Saving customer data********");
		addCust.clickOnSave();
		Thread.sleep(3000);

	}

	//Below method is validation part
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String meg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));

	}



	//Steps for searching a customer using Email ID....................
	@And("Enter customer EMail")
	public void enter_customer_EMail() {
		logger.info("********Searching customer by email id********");
		searchCust= new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
		//searchCust.setEmail("james_pan@nopCommerce.com");

	}

	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);

	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		boolean status=searchCust.searchCustomersByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}



	//Steps for searching a customer by first and last name
	@And("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("********Searching customer by name********");
		
		searchCust=new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");

	}

	@And("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCust=new SearchCustomerPage(driver);
		searchCust.setLastName("Terces");

	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		boolean status=searchCust.searchCustomersByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}
}




