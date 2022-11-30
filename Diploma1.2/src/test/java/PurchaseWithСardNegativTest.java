import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class PurchaseWithСardNegativTest {
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
    void paymentForDebitCardLimitValueMonthAfterMax() {
        $$("button").find(Condition.exactText("Купить")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 4441");
        $(byText("Месяц")).parent().$(".input__control").setValue("13");
        $(byText("Год")).parent().$(".input__control").setValue("22");
        $(byText("Владелец")).parent().$(".input__control").setValue("Ivanov Ivan");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Неверно указан срок действия карты")).parent().$(".input__sub");
    }

    @Test
    void paymentForDebitCardLimitValueMonthBeforeMin() {
        $$("button").find(Condition.exactText("Купить")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 4441");
        $(byText("Месяц")).parent().$(".input__control").setValue("0");
        $(byText("Год")).parent().$(".input__control").setValue("22");
        $(byText("Владелец")).parent().$(".input__control").setValue("Ivanov Ivan");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Неверно указан срок действия карты")).parent().$(".input__sub");

    }

    @Test
    void submitAnEmptyForm() {
        $$("button").find(Condition.exactText("Купить")).click();
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Неверный формат")).parent().$(".input__sub");
        $(byText("Поле обязательно для заполнения")).parent().$(".input__sub");
    }

    @Test
    void paymentForDebitCardLimitValueNumberCardAnderMax() {
        $$("button").find(Condition.exactText("Купить")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 444");
        $(byText("Месяц")).parent().$(".input__control").setValue("08");
        $(byText("Год")).parent().$(".input__control").setValue("22");
        $(byText("Владелец")).parent().$(".input__control").setValue("Ivanov Ivan");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Неверный формат")).parent().$(".input__sub");
    }
    @Test
    void paymentForDebitCardLimitValueBeforeYears() {
        $$("button").find(Condition.exactText("Купить")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 4441");
        $(byText("Месяц")).parent().$(".input__control").setValue("08");
        $(byText("Год")).parent().$(".input__control").setValue("21");
        $(byText("Владелец")).parent().$(".input__control").setValue("Ivanov Ivan");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Истёк срок действия карты")).parent().$(".input__sub");
    }
    @Test
    void paymentForDebitCardLimitValueAnderYears() {
        $$("button").find(Condition.exactText("Купить")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 4441");
        $(byText("Месяц")).parent().$(".input__control").setValue("08");
        $(byText("Год")).parent().$(".input__control").setValue("28");
        $(byText("Владелец")).parent().$(".input__control").setValue("Ivanov Ivan");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Неверно указан срок действия карты")).parent().$(".input__sub");
    }
    @Test
    void paymentForDebitCardLastNameWithOneLetter() {
        $$("button").find(Condition.exactText("Купить")).click();
        $(byText("Номер карты")).parent().$(".input__control").setValue("4444 4444 4444 4441");
        $(byText("Месяц")).parent().$(".input__control").setValue("12");
        $(byText("Год")).parent().$(".input__control").setValue("22");
        $(byText("Владелец")).parent().$(".input__control").setValue("I");
        $(byText("CVC/CVV")).parent().$(".input__control").setValue("999");
        $$("button").find(Condition.exactText("Продолжить")).click();
        $(byText("Успешно")).parent().$(".notification");
    }
}
