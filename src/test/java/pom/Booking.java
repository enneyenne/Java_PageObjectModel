package pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;


public class Booking {

    WebDriver driver;
    Actions action;

    @FindBy(xpath =  "//button[contains(@type, \"button\")] /following::div[contains(text(), \"Управление бронированием\")]")
    WebElement bookingManagementItem;

    @FindBy(xpath =  "//input[@placeholder=\"Номер бронирования или билета\"] ")
    WebElement ticketNumber;

    @FindBy(xpath = "//input[@placeholder=\"Фамилия клиента\"]")
    WebElement surname;

    @FindBy(xpath = "//button[@type=\"submit\"] [text()=\"Поиск\"]")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class=\"message_error\"] [text()=\"Заказ с указанными параметрами не найден\"]")
    WebElement searchErrorText;

    public Booking(WebDriver driver, Actions action) {
        this.driver = driver;
        this.action = action;
        PageFactory.initElements(driver, this);
    }

    public WebElement getbookingManagementItem() {
        return bookingManagementItem;
    }

    public void mouseoverBookingManagementItem(WebElement bookingManagementItem) {
        Actions action = new Actions(driver);
        action.moveToElement(bookingManagementItem).perform();
        bookingManagementItem.click();
    }

    public WebElement getTicketNumber() {
        return ticketNumber;
    }

    public WebElement getSurname() {
        return surname;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getSearchErrorText() {
        return searchErrorText;
    }

    public void setTicketNumber(String enterTicketNumber) {
        Actions action = new Actions(driver);
        action.moveToElement(ticketNumber).perform();
        ticketNumber.sendKeys(enterTicketNumber);
        ticketNumber.sendKeys(Keys.ENTER);
    }

    public void setSurname(String enterSurname) {
        Actions action = new Actions(driver);
        action.moveToElement(surname).perform();
        surname.sendKeys(enterSurname);
        surname.sendKeys(Keys.ENTER);
    }

    public void clickSearchButton() {
        Actions action = new Actions(driver);
        action.moveToElement(searchButton).perform();
        searchButton.click();
        searchButton.sendKeys(Keys.ENTER);
    }

    public void switchToNewWindow() {
        String oldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
    }

}
