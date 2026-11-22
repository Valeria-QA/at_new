package selenide_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class OrderTest  {
    int createdGoodId;
    String goodName;

    @BeforeEach
    void setup() {
        goodName = "Order_" + new Random().nextInt(1000, 9999);
        createdGoodId = ApiUtils.createGood(goodName, 50.2);
    }

    @Test
    void orderThreeItemsTest() {
        open("http://localhost:8080/");

        for (int i = 0; i < 3; i++) {
            $("button[data-action='add-to-cart'][data-id='" + createdGoodId + "']").click();
        }

        $("#open-cart-btn").click();

        String priceText = $("#total-price").getText();
        double actualTotal = Double.parseDouble(priceText);

        Assertions.assertThat(actualTotal)
                .as("verification sum")
                .isEqualTo(150.60);

        $("#makeOrder").click();

        $x("//*[@id='toast-container']").shouldBe(visible);
    }

    @AfterEach
    void tearDown() {
        if (createdGoodId != 0) {
            ApiUtils.deleteGood(createdGoodId);
        }
    }
}
