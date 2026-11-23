package selenide_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class DragAndDropTest {

    int createdGoodId;
    String goodName;

    @BeforeEach
    void setup() {
        goodName = "DND_Test_" + new Random().nextInt(1000, 9999);
        createdGoodId = ApiUtils.createGood(goodName, 50.0);

        open("http://localhost:8080/");
    }

    @Test
    void simpleDND() {
        SelenideElement firstCard = $(".product-card[data-id='" + createdGoodId + "']");
        SelenideElement cartButton = $("#open-cart-btn");
        sleep(1000);

        firstCard.dragAndDrop(DragAndDropOptions.to(cartButton));
        sleep(2000);

        cartButton.click();

        $("#cart-item-" + createdGoodId).shouldBe(visible);
    }

     @AfterEach
    void tearDown() {
        if (createdGoodId != 0) {
            ApiUtils.deleteGood(createdGoodId);
        }
    }
}
