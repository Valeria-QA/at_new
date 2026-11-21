package web_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;

public class AddProductTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login");
    }

    @Test
    void addProductAndCheckOnShopTest() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("secret123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n-name")));

        driver.findElement(By.id("n-name")).sendKeys("Тестовый товар");
        driver.findElement(By.id("n-price")).sendKeys("25");
        driver.findElement(By.id("add-btn")).click();

        driver.get("http://localhost:8080/");

        WebElement titleElement = driver.findElement(
                By.xpath("//div[contains(@class, 'product-card')]//h4[contains(text(), 'Тестовый товар')]")
        );

        assertThat(titleElement.isDisplayed())
                .as("verification name")
                .isTrue();

        WebElement priceElement = driver.findElement(
                By.xpath("//div[contains(@class, 'product-card')][.//h4[contains(text(), 'Тестовый товар')]]//div[contains(text(), '25')]")
        );

        assertThat(priceElement.getText())
                .as("verification price")
                .contains("25");
    }

    @AfterEach
    void tearDown() {
        driver.quit(); // Закрытие браузера
    }
}
