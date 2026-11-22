package selenide_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;

public class MultipleItemsCartTest {
    int firstGoodId = 0;
    int secondGoodId = 0;
    String firstName;
    String secondName;

    @BeforeEach
    void setup() {
        int randomNum = new Random().nextInt(1000, 9999);
        firstName = "Item1_" + randomNum;
        secondName = "Item2_" + randomNum;

        firstGoodId = ApiUtils.createGood(firstName, 100.0);
        secondGoodId = ApiUtils.createGood(secondName, 50.0);
    }

    @Test
    void cartTotalPriceTest() {
        open("http://localhost:8080/");

        $("button[data-action='add-to-cart'][data-id='" + firstGoodId + "']").click();
        $("button[data-action='add-to-cart'][data-id='" + secondGoodId + "']").click();
        $("#open-cart-btn").click();

        String priceText = $("#total-price").getText();

        double actualTotal = Double.parseDouble(priceText);

        org.assertj.core.api.Assertions.assertThat(actualTotal)
                .as("Verification sum")
                .isEqualTo(150.0);
    }

    @AfterEach
    void tearDown() {
        if (firstGoodId != 0) {
            ApiUtils.deleteGood(firstGoodId);
        }
        if (secondGoodId != 0) {
            ApiUtils.deleteGood(secondGoodId);
        }
    }
}
