
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.*;

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
}
