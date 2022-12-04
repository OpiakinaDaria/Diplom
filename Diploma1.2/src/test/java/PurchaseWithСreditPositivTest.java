import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PurchaseWithСreditPositivTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        closeWebDriver();
    }

    @Test
    void paymentForCredit() {
        $$("button").find(Condition.exactText("Купить в кредит")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 4441");
        $(byText("Месяц")).parent().$(".input__control").setValue("11");
        $(byText("Год")).parent().$(".input__control").setValue("22");
        $(byText("Владелец")).parent().$(".input__control").setValue("Ivanov Ivan");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Успешно")).parent().$(".notification");
    }
}
