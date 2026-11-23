package selenide_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class RemoveFromCartTest {

    int createdGoodId;

    @BeforeEach
    void setup() {
        String goodName = "Remove_" + new Random().nextInt(1000, 9999);
        createdGoodId = ApiUtils.createGood(goodName, 50.00);

        open("http://localhost:8080/");
    }

    @Test
    void removeItemFromCartTest() {
        $("button[data-action='add-to-cart'][data-id='" + createdGoodId + "']").click();
        $("#open-cart-btn").click();

        SelenideElement cartItem = $("#cart-item-" + createdGoodId);
        cartItem.shouldBe(visible);

        cartItem.$("button[data-action='remove']").click();
        cartItem.shouldNotBe(visible);
    }

    @AfterEach
    void tearDown() {
        if (createdGoodId != 0) {
            ApiUtils.deleteGood(createdGoodId);
        }
    }
}
