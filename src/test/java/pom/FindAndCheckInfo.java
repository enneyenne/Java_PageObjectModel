package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindAndCheckInfo {

    WebDriver driver;
    Actions action;

    @FindBy(xpath =  "//a[@href=\"/information\"]")
    WebElement informationItem;

    @FindBy(xpath = "//a[@href=\"/information#flight\"]")
    WebElement preparingFlight;

    @FindBy(xpath = "//a[@href=\"/information#useful\"]")
    WebElement helpfulInfo;

    @FindBy(xpath = "//a[@href=\"/information#company\"]")
    WebElement aboutCompany;

    public FindAndCheckInfo(WebDriver driver, Actions action) {
        this.driver = driver;
        this.action = action;
        PageFactory.initElements(driver, this);
    }

    public WebElement getInformationItem() {
        return informationItem;
    }

    public void mouseoverInformationItem(WebElement informationItem) {
        Actions action = new Actions(driver);
        action.moveToElement(informationItem).build().perform();
    }

    public WebElement getPreparingFlight() {
        return preparingFlight;
    }

    public WebElement getHelpfulInfo() {
        return helpfulInfo;
    }

    public WebElement getAboutCompany() {
        return aboutCompany;
    }

}
