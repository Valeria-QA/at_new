package selenide_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class EditProductTest {

    int createdGoodId;
    String oldName;
    String newName;

    @BeforeEach
    void setup() {
        int randomNum = new Random().nextInt(1000, 9999);
        oldName = "Edit_Old_" + randomNum;
        newName = "Edit_New_" + randomNum;

        createdGoodId = ApiUtils.createGood(oldName, 15.0);
    }

    @Test
    void editGoodTest() {
        open("http://localhost:8080/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        $("#nm-" + createdGoodId).setValue(newName);

        $("button[data-action='update'][data-id='" + createdGoodId + "']").click();

        open("http://localhost:8080/");

        $x("//div[contains(@class, 'product-card')]//h4[text()='" + newName + "']").shouldBe(visible);

        $x("//div[contains(@class, 'product-card')]//h4[text()='" + oldName + "']").shouldNotBe(visible);
    }

    @AfterEach
    void tearDown() {
        if (createdGoodId != 0) {
            ApiUtils.deleteGood(createdGoodId);
        }
    }
}
