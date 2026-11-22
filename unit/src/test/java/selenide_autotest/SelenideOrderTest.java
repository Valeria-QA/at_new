package selenide_autotest;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.*;

public class SelenideOrderTest {

    @Test
    void orderMoreThan300RublesTest() {
        open("http://localhost:8080/");

        SelenideElement addToCartBtn = $x("//*[@data-action='add-to-cart']");

        for (int i = 0; i < 15; i++) {
            addToCartBtn.click();
        }

        $("#open-cart-btn").click();
        $("#makeOrder").click();

        Alert activeAlert = Selenide.switchTo().alert();

        String alertText = activeAlert.getText();
        System.out.println("Alert text: " + alertText);

        Assertions.assertThat(alertText)
                .as("verification text")
                .isNotBlank();

        activeAlert.accept();
    }
}