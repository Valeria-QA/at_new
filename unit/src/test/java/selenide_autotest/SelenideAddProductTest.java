package selenide_autotest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideAddProductTest {

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
    }

    @Test
    void addProductAndCheckOnShopTest() {
        open("http://localhost:8080/login");

        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();


        $("#n-name").setValue("Стакан");
        $("#n-price").setValue("15");
        $("#add-btn").click();

        open("http://localhost:8080/");

        $x("//div[contains(@class, 'product-card')]//h4[contains(text(), 'Стакан')]")
                .shouldBe(visible);
    }
}
