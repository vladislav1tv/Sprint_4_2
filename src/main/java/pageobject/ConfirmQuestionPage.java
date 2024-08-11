package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmQuestionPage {

    private WebDriver driver;

    // Локатор кнопки подтверждения заказа
    private By confirmButton = By.xpath(".//button[text()='Да']");

    public ConfirmQuestionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }
}