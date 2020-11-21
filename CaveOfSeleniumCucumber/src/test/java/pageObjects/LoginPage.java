package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver ldriver;
    
	public LoginPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
    }


    public void setUserName(String uname) {
        txtEmail.clear();
        txtEmail.sendKeys(uname);     
    }
 
    // Create locator/identifier
    @FindBy(id="Email")
    @CacheLookup
    WebElement txtEmail;

    public void setPassword(String pwd) {
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }

    @FindBy(id = "Password")
    @CacheLookup
    WebElement txtPassword;
    
    
    public void clickLogin() {
        btnLogin.click();
    }

    @FindBy(xpath="//input[@value='Log in']")
    @CacheLookup
    WebElement btnLogin;

    public void clickLogout() {
        lnkLogout.click();
    }

    @FindBy(linkText = "Logout")
    @CacheLookup
    WebElement lnkLogout;
}

