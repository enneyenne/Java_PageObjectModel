package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    WebDriver driver;

    @FindBy(xpath = "//img[contains(@src, \"logo-rus\") " +
            "and contains(@alt, \"«Авиакомпания «Победа», Группа «Аэрофлот»\")]")
    WebElement pobedaImg;

//    By pobedaImg = By.xpath("//img[contains(@src, \"logo-rus\") " +
//            "and contains(@alt, \"«Авиакомпания «Победа», Группа «Аэрофлот»\")]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

//    public WebElement getPobedaImg() {
//        return driver.findElement(pobedaImg);
//    }

    public WebElement getPobedaImg() {
        return pobedaImg;
    }

}
