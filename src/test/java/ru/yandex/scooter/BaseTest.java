package ru.yandex.scooter;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// Базовый тестовый класс, в котором настраивается драйвер.
public class BaseTest {
    //Объявляем переменную драйвера
    protected WebDriver driver;

    @Before
    public void startUp() {
        // WebDriverManager.ChromeDriver().setup();
        driver = new ChromeDriver();
        // driver = new ChromeDriver();
    }

    @After
    public void cleanUp() {
        driver.quit(); // Закрываем сессию драйвера
    }

    // Неявное ожидание
    public void implicitlyWait(long numberOfSeconds) {
        driver.manage().timeouts().implicitlyWait(numberOfSeconds, TimeUnit.SECONDS);
    }

}
