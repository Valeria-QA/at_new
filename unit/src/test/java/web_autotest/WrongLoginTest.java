package web_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.assertj.core.api.Assertions;

public class WrongLoginTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login");
    }

    @Test
    void loginWithInvalidCredentialsTest() {
        driver.findElement(By.id("username")).sendKeys("wrong_user");

        driver.findElement(By.id("password")).sendKeys("wrong_password");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean isLoginFormStillVisible = driver.findElement(By.cssSelector("form.login-form")).isDisplayed();

        Assertions.assertThat(isLoginFormStillVisible)
                .as("verification error")
                .isEqualTo(true);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
