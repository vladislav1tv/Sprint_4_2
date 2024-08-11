package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class SuccessOrderCreationPage {

    private WebDriver driver;

    // Локатор об успешном создании заказа
    private By successOrderMessage = By.xpath(".//div[text()='Заказ оформлен']");

    public SuccessOrderCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSuccessOrderCreationMessageVisible() {
        return driver.findElement(successOrderMessage).isDisplayed();
    }
}