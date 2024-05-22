package pom;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

public class Testing {

    static WebDriver driver = new ChromeDriver();
    static WebDriverWait wait;
    static Actions action;

    MainPage mainPage;
    FindAndCheckInfo findAndCheckInfo;
    Search search;
    Booking booking;

    @BeforeEach
    public void test_openBrowser() {

        // Implicit
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //Explicit
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Переход по ссылке
        driver.get("https://pobeda.aero");
    }

    @BeforeEach
    @Test
    public void test_checkTitleAndLogo() {

        mainPage = new MainPage(driver);

        // Проверка заголовка
        String currentPageTitle = mainPage.getPageTitle();

        Assertions.assertTrue(currentPageTitle
                .contains("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));

        // Проверка изображения
        WebElement pobedaImg = mainPage.getPobedaImg();

        with()
                .pollDelay(100, TimeUnit.MILLISECONDS)
                .await()
                .atMost(5, TimeUnit.SECONDS)
                .until(pobedaImg::isDisplayed);

        Assertions.assertEquals(pobedaImg.getAttribute("alt"), "«Авиакомпания «Победа», Группа «Аэрофлот»");
    }

    @Test
    public void test_findAndCheckInfo() {

        findAndCheckInfo = new FindAndCheckInfo(driver, action);

        // Проверка пункта "Информация"
        WebElement informationItem = findAndCheckInfo.getInformationItem();
        Assertions.assertEquals(informationItem.getText(), "Информация");

        // Наведение на пункт "Информация"
        findAndCheckInfo.mouseoverInformationItem(informationItem);

        // Проверка отображения элементов
        WebElement preparingFlight = findAndCheckInfo.getPreparingFlight();
        WebElement helpfulInfo = findAndCheckInfo.getHelpfulInfo();
        WebElement aboutCompany = findAndCheckInfo.getAboutCompany();

        wait.until(ExpectedConditions.visibilityOf(preparingFlight));
        wait.until(ExpectedConditions.visibilityOf(helpfulInfo));
        wait.until(ExpectedConditions.visibilityOf(aboutCompany));

        Assertions.assertTrue(helpfulInfo.isDisplayed());
        Assertions.assertTrue(preparingFlight.isDisplayed());
        Assertions.assertTrue(aboutCompany.isDisplayed());
    }

    @Test
    public void test_searchTickets() {

        search = new Search(driver, action);

        // Проверка отображения элементов
        WebElement cityFrom = search.getCityFrom();
        WebElement cityTo = search.getCityTo();

        wait.until(ExpectedConditions.elementToBeClickable(cityFrom));
        wait.until(ExpectedConditions.elementToBeClickable(cityTo));

        Assertions.assertTrue(cityFrom.isDisplayed());
        Assertions.assertTrue(cityTo.isDisplayed());

        // Ввод даннных
        search.setCityFrom("Москва");
        search.setCityTo("Санкт-Петербург");

        // Поиск
        search.clickSearchButton();
    }

    @Test
    public void test_booking() {

        booking = new Booking(driver, action);

        // Переход в пункт "Управление бронированием"
        WebElement bookingManagementItem = booking.getbookingManagementItem();
        booking.mouseoverBookingManagementItem(bookingManagementItem);

        // Проверка отображения элементов
        WebElement ticketNumber = booking.getTicketNumber();
        WebElement surname = booking.getSurname();
        WebElement searchButton = booking.getSearchButton();

        wait.until(ExpectedConditions.visibilityOf(ticketNumber));
        wait.until(ExpectedConditions.visibilityOf(surname));
        wait.until(ExpectedConditions.visibilityOf(searchButton));

        Assertions.assertTrue(ticketNumber.isDisplayed());
        Assertions.assertTrue(surname.isDisplayed());
        Assertions.assertTrue(searchButton.isDisplayed());

        // Ввод данных
        booking.setTicketNumber("XXXXXX");
        booking.setSurname("Qwerty");

        // Поиск
        booking.clickSearchButton();

        // Переключение фокуса на новое открытое окно
        booking.switchToNewWindow();

        // Проверка наличия текста с ошибкой
        booking.getSearchErrorText();
        WebElement errorText = booking.getSearchErrorText();
        Assertions.assertEquals(errorText.getAttribute("outerText"), "Заказ с указанными параметрами не найден");
    }

    @AfterAll
    public static void test_closeBrowser() throws InterruptedException {

        // Завершенеие работы браузера
        Thread.sleep(5000);
        driver.quit();
    }
}