package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentPage {

    private WebDriver driver;

    // Локатор поля даты доставки
    private By deliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // Локатор поля срока аренды
    private By rentalPeriodField = By.cssSelector("div.Dropdown-root");
    // Локатор поля комментарии
    private By commentField = By.cssSelector("input[placeholder='Комментарий для курьера']");
    // Локатор кнопки подтверждения заказа
    private By orderConfirmButton =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitRentPageWillBeLoaded() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(deliveryDateInput));
    }

    public void enterValidDeliveryDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Получаем текущую дату, прибавляем к ней 1 день
        String date = LocalDate.now().plusDays(1).format(formatter);
        driver.findElement(deliveryDateInput).sendKeys(date); // Вводим дату в поле с датой доставки
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER); // Нажимаем Enter
    }

    // Метод выбирает срок аренды из выпадающего меню
    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click(); // Клик на поле выбора срока аренды самоката
        // Выбираем срок аренды самоката из выпадающего списка и кликаем на него
        String rentalPeriodLocator = String.format(".//div[text()='%s']", rentalPeriod);
        driver.findElement(By.xpath(rentalPeriodLocator)).click();
    }

    // Метод цвет самоката
    public void chooseColour(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void fillRequiredFields(String rentalPeriod, String colour, String comment) {
        enterValidDeliveryDate();
        chooseRentalPeriod(rentalPeriod);
        chooseColour(colour);
        setComment(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderConfirmButton).click();
    }
}
