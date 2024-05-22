package pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {

    WebDriver driver;
    Actions action;

    @FindBy(xpath =  "//input[@placeholder=\"Откуда\"] ")
    WebElement cityFrom;

    @FindBy(xpath = "//input[@placeholder=\"Куда\"]")
    WebElement cityTo;

    @FindBy(xpath = "//button[@type=\"submit\"] [text()=\"Поиск\"]")
    WebElement searchButton;

    //@FindBy(xpath = "//input[contains(@placeholder, \"Туда\")] /following::div[contains(@data-errored, \"true\")]")
    @FindBy(xpath = "//div[contains(@class, \"dp-1bgth1z-root\")] [contains(@data-errored, \"true\")]")
    WebElement redButton;

    public Search(WebDriver driver, Actions action) {
        this.driver = driver;
        this.action = action;
        PageFactory.initElements(driver, this);
    }

    public WebElement getCityFrom() {
        return cityFrom;
    }

    public WebElement getCityTo() {
        return cityTo;
    }

    public void setCityFrom(String enterCityFrom) {
        Actions action = new Actions(driver);
        action.moveToElement(cityFrom).perform();
        cityFrom.sendKeys(enterCityFrom);
        cityFrom.sendKeys(Keys.ARROW_DOWN);
        cityFrom.sendKeys(Keys.ENTER);
    }

    public void setCityTo(String enterCityTo) {
        Actions action = new Actions(driver);
        action.moveToElement(cityTo).perform();
        cityTo.sendKeys(enterCityTo);
        cityTo.sendKeys(Keys.ARROW_DOWN);
        cityTo.sendKeys(Keys.ENTER);
    }

    public void clickSearchButton() {
        Actions action = new Actions(driver);
        action.moveToElement(searchButton).perform();
        searchButton.click();
        searchButton.sendKeys(Keys.ENTER);
    }

}
