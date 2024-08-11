package ru.yandex.scooter;

import pageobject.*;
import pageobject.constants.OrderButton;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class OrderScooterTest extends BaseTest {

    // Поля класса
    // Kнопка заказа (сверху или снизу)
    private final String orderButton;
    // Имя
    private final String name;
    // Фамилия
    private final String surname;
    // Адрес доставки
    private final String address;
    // Номер телефона
    private final String phoneNumber;
    // Период аренды
    private final String rentalPeriod;
    // Цвет самоката
    private final String colour;
    // Комментарий
    private final String comment;
    // Результат
    private boolean actual;

    public OrderScooterTest(String orderButton, String name, String surname, String address, String phoneNumber,
                            String rentalPeriod, String colour, String comment)
    {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderFormData() {
        return new Object[][] {
                {OrderButton.TOP_BUTTON, "Райан", "Гослинг", "Москва", "89099099999", "сутки", "black", "1"},
                {OrderButton.BOTTOM_BUTTON, "Скала",
                        "Джонсон",
                        "Москва, ул. Краснознаменская, дом 5 квартира 25",
                        "+79055555555", "семеро суток", "grey",
                        "Жду самокат"
                                + "Звоните заранее"
                },
        };
    }

    // Проверяем весь флоу позитив
    @Test
    public void checkOrderScooterValidData_expectScooterIsOrdered() {
        super.implicitlyWait(3);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton(orderButton);

        OrderFormPage orderFormPage = new OrderFormPage(driver);
        orderFormPage.fillAllRequiredFields(name, surname, address, phoneNumber);
        orderFormPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.waitRentPageWillBeLoaded();
        rentPage.fillRequiredFields(rentalPeriod, colour, comment);
        rentPage.clickOrderButton();

        ConfirmQuestionPage confirmQuestionPage = new ConfirmQuestionPage(driver);
        confirmQuestionPage.clickConfirmButton();

        SuccessOrderCreationPage successOrderCreationPage = new SuccessOrderCreationPage(driver);
        actual = successOrderCreationPage.isSuccessOrderCreationMessageVisible();
        Assert.assertTrue("Expected: a message is displayed that the order was created successfully ",
                actual);
    }
}
