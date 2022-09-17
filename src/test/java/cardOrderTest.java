
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.RGBColor;

import java.awt.*;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class cardOrderTest {

    @Test
    void cardRequestSuccess() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Сергей Сучок");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $("[data-test-id=order-success]").shouldHave(matchText("Ваша заявка успешно отправлена!"));
    }

    @Test
    void cardRequestWrongName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("WrongName");
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid").$(".input__sub").shouldHave(matchText("Имя и Фамилия указаные неверно."));
    }

    @Test
    void cardRequestWrongPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Сергей Сучок");
        form.$("[data-test-id=phone] input").setValue("79991234567");
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid").$(".input__sub").shouldHave(matchText("Телефон указан неверно."));
    }

    @Test
    void cardRequestEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void cardRequestEmptyPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Сергей Сучок");
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid").$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void cardPermissionNotGiven() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Сергей Сучок");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        assertEquals(Color.decode("#ff5c5c"), CssValueColorParser.parse($(".input_invalid").$(".checkbox__text").getCssValue("color")));
    }
}
