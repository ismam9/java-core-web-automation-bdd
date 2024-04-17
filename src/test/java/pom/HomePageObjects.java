package pom;

import config.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects{

    public HomePageObjects() {
        PageFactory.initElements(WebDriver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id='map']")
    public WebElement Cookies;
}
