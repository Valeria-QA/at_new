package selenide_autotest;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideAddToCartTest {

    @Test
    void addToCartAndCheckTest() {
        open("http://localhost:8080/");

        String expectedProductName = $(".product-card h4").getText();

        $(".product-card button[data-action='add-to-cart']").click();

        $("#open-cart-btn").click();

        $("#cart-items").shouldHave(text(expectedProductName));
    }
}