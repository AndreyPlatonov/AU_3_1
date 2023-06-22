package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @Test
    void shouldSubmitSuccessRequest() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldSubmitNullName() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$(".button").click();
        List<SelenideElement> subs = $$(".input__sub");
        subs.get(0).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitWrongName() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий22");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        List<SelenideElement> subs = $$(".input__sub");
        subs.get(0).shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitNullPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий иванов");
        form.$(".button").click();
        List<SelenideElement> subs = $$(".input__sub");
        subs.get(1).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitWrongPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий иванов");
        form.$("[data-test-id=phone] input").setValue("222222");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        List<SelenideElement> subs = $$(".input__sub");
        subs.get(1).shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitNoCheck() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form_theme_alfa-on-white");
        form.$("[data-test-id=name] input").setValue("Василий иванов");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".button").click();
        form.$("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));

    }
}