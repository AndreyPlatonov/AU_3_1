package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSubmitSuccessRequest() {

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldSubmitNullName() {

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitWrongName() {

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий22");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitNullPhone() {

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий иванов");
        form.$("[data-test-id=phone] input").setValue("");
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitWrongPhone() {

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий иванов");
        form.$("[data-test-id=phone] input").setValue("222222");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitNoCheck() {

        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий иванов");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".button").click();
        form.$("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));

    }
}