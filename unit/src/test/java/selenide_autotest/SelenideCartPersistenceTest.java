package selenide_autotest;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class SelenideCartPersistenceTest {

    @Test
    void cartShouldPersistAfterRefreshTest() {
        open("http://localhost:8080/");

        String expectedProductName = $(".product-card h4").getText();

        $(".product-card button[data-action='add-to-cart']").click();

        refresh();

        $("#open-cart-btn").click();

        $$("#cart-items .cart-item")
                .findBy(text(expectedProductName))
                .shouldBe(visible);
    }
}
