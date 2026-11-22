package selenide_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class AddProductAlert {
    int createdGoodId = 0;

    @Test
    void addGoodViaUiAndCheckNotificationTest() {
        // 1. Заходим в админку
        open("http://localhost:8080/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        String goodName = "UI_Test_" + new Random().nextInt(1000, 9999);
        $("#n-name").setValue(goodName);
        $("#n-price").setValue("100");
        $("#add-btn").click();

        $x("//*[@id='toast-container']").shouldBe(visible);

        createdGoodId = ApiUtils.getGoodIdByName(goodName);
    }

    @AfterEach
    void tearDown() {
        if (createdGoodId != 0) {
            ApiUtils.deleteGood(createdGoodId);
        }
    }
}
