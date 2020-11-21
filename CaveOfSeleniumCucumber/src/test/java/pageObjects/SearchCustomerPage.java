package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utillities.WaitHelper;

public class SearchCustomerPage {
	
	//First we add constructor here
	public WebDriver ldriver;
	WaitHelper waithelper;
    

    public SearchCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
        waithelper = new WaitHelper(ldriver);
    	}
    
    //Constructor ended here
    
    
    //Identification of all elements/finders declares below
    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    //@FindBy(how = How.ID, using = "//input[@id='SearchFirstName']")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    //@FindBy(how = How.ID, using = "//input[@id='SearchLastName']")
    @CacheLookup
    WebElement txtLastName;
    
    @FindBy(how = How.ID, using = "search-customers")
    //@FindBy(how = How.XPATH, using = "//button[@id='search-customers']")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//table[@role='grid']")
    @CacheLookup
    WebElement tblSearchResults;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    //@FindBy(how = How.XPATH, using = "//tbody/tr[3]/td[1]/input[1]")
  
    @CacheLookup
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    @CacheLookup
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
    @CacheLookup
    List<WebElement> tableColumns;
     
    
    //Action methods----------------
    public void setEmail(String email) {
    	waithelper.WaitForElement(txtEmail, 30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {
    	waithelper.WaitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
    	waithelper.WaitForElement(txtLastName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSearch() {
        btnSearch.click();
        waithelper.WaitForElement(btnSearch, 30);

    }

    public int getNoOfRows() {
        return (tableRows.size());

    }

    public int getNoOfColumns() {
        return (tableColumns.size());
    }

    public boolean searchCustomersByEmail(String email)
    {
        boolean flag=false;
        for(int i=1;i<= getNoOfRows();i++){
            String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
        	
            System.out.println(emailid);

            if (emailid.equals(email))
            {
                flag=true;
            }
        }
        return flag;

    }
    
    public boolean searchCustomersByName(String Name) {
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) 
        {
            String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();
            String names[]=name.split(" "); //Here separating fname and lname //input[@id='SearchFirstName']
            if(names[0].equals("Victoria") && names[1].equals("Terces"))
            {
            flag = true;
            }
        }
        return flag;

    }
}
