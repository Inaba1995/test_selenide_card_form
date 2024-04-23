package ru.netollogy.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(text);

        $("[data-test-id='name']").$("[class='input__control']").setValue("Авдеева Елена Алексеевна");
        $("[data-test-id='phone']").$("[class='input__control']").setValue("+79991233443");
        $("[data-test-id='agreement']").click();

        $(".button").click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + text), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
