package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;


public class BaseClass {
		//Create Webdriver (This two variables we create here we use through all methods here and LoginPage.java)
		public WebDriver driver; 
		//Due to this will used in all classes and we move from steps.java in to the base class and base class should be extends to all classes 
		public LoginPage lp; 
		//And create a object for all action in Addcustomer.java  here
		public AddCustomerPage addCust;
		public SearchCustomerPage searchCust;
		public static Logger logger;
		public Properties configProp;
		
		//Created for generating random string for unique email
		public static String randomString() {
	        String generatedString1 = RandomStringUtils.randomAlphanumeric(5);
	        return (generatedString1);

	    }
	}
