package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	//This constructor is create a WebDriver object and initiate it 
	public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);

    }
    //Below are all locators for every element
    By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
    By getLnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customer')]");
    
	By btnAddnew = By.xpath("//a[@class='btn bg-blue']"); //Add new 
    
	By txtEmail = By.xpath("//input[@id='Email']");
	
	By txtPassword = By.xpath("//input[@id='Password']");
	// it call from here public void setPassword(String pwd) { 
	// txtPassword.clear();//        txtPassword.sendKeys(pwd);
    
	By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
    
	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");

    By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
    By rdMaleGender = By.id("Gender_Male");
    By rdFeMaleGender = By.id("Gender_FeMale");

    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLaststName = By.xpath("//input[@id='LastName']");

    By txtBob = By.xpath("//input[@id='DateOfBirth']");
    By txtCompanyName = By.xpath("//input[@id='Company']");

    By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
    By btnSave = By.xpath("//button[@name='save']");
      
	//Action Methods
   
    //Below method is added here to capture a page title 
	public String getPageTitle()
	{
	    return ldriver.getTitle();
	}
	
	//From here we add all methods which interact with above locators
	public void clickOnCustomersMenu() {
	    ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomerMenuItem() {
	    ldriver.findElement(getLnkCustomers_menuitem).click();
	}
	
	public void clickOnAddnew() {
	    ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
	    ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
	    ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setLnkCustomerRoles(String role) throws InterruptedException {
	
	    if (!role.equals("Vendors")) {
	        ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li[1]/span[2]")).click();
	        //Below captured with ChroPath plugin
	        //ul[@id='SelectedCustomerRoleIds_taglist']

	    }
	    
	    ldriver.findElement(txtcustomerRoles).click();
	    
	    WebElement listitem;
	    Thread.sleep(3000);
	    
	    if (role.equals("Administrators")) {
	        listitem = ldriver.findElement(lstitemAdministrators);
	    } else if (role.equals("Guests")) {
	        listitem = ldriver.findElement(lstitemGuests);
	    } else if (role.equals("Registered")) {
	        listitem = ldriver.findElement(lstitemRegistered);
	    } else if (role.equals("Vendors")) {
	        listitem = ldriver.findElement(lstitemVendors);
	    } else {
	        listitem = ldriver.findElement(lstitemGuests);
	    }
	   
	    //listitem.click();
	    JavascriptExecutor js = (JavascriptExecutor) ldriver;
	    js.executeScript("arguments[0].click();", listitem);
	}
	
	public void setManagerOfVendor(String value) {
	    Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
	    drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
	    if (gender.equals("Male")) {
	        ldriver.findElement(rdMaleGender).click();
	    } else if (gender.equals("Female")) {
	        ldriver.findElement(rdFeMaleGender).click();
	    } else {
	        ldriver.findElement(rdMaleGender).click(); //Default
	    }
	}
	
	public void setFirstName(String fname) {
	    ldriver.findElement(txtFirstName).sendKeys(fname);
	
	}
	
	public void setLastName(String lname) {
	    ldriver.findElement(txtLaststName).sendKeys(lname);
	}
	
	public void setDob(String dob) {
	    ldriver.findElement(txtBob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname) {
	    ldriver.findElement(txtCompanyName).sendKeys(comname);
	
	}
	
	public void setAdminContent(String content) {
	    ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
	    ldriver.findElement(btnSave).click();
	}
	
}
	
	
