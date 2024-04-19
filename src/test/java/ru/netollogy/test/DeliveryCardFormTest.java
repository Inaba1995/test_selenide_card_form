package ru.netollogy.test;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardFormTest {
    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999");
        $("[data-test-id='city']").$("[class='input__control']").setValue("Калининград");

        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        $("[data-test-id='date']").$("[class='input__control']").setValue("parseDate");

        $("[data-test-id='name']").$("[class='input__control']").setValue("Авдеева Елена Алексеевна");
        $("[data-test-id='phone']").$("[class='input__control']").setValue("+79991233443");
        $("[data-test-id='agreement']").click();

        $(".button").click();

        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}