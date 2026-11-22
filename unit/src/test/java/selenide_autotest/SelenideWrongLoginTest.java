package selenide_autotest;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideWrongLoginTest {

    @Test
    void loginWithInvalidCredentialsTest() {
        open("http://localhost:8080/login");

        $("#username").setValue("wrong_user");
        $("#password").setValue("wrong_password");

        $("button[type='submit']").click();

        $("form.login-form").shouldBe(visible);
    }
}