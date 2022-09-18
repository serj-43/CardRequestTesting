
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {

    @BeforeEach
    void initOperations(){
        open("http://localhost:9999");
    }
    @Test
    void cardRequestSuccess() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Андрей Попов-Петров");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void cardRequestWrongName() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("WrongName");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        form.$("[data-test-id='name']").$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void cardRequestWrongPhone() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Андрей Попов-Петров");
        form.$("[data-test-id=phone] input").setValue("79991234567");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        form.$("[data-test-id='phone']").$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void cardRequestEmptyName() {
        SelenideElement form = $("form");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        form.$("[data-test-id='name']").$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void cardRequestEmptyPhone() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Андрей Попов-Петров");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        form.$("[data-test-id='phone']").$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void cardPermissionNotGiven() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Андрей Попов-Петров");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("button").click();
        form.$("[data-test-id=agreement]").$(".input__invalid").isDisplayed();
    }
}
