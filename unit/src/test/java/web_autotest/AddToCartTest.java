package web_autotest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.assertj.core.api.Assertions;

public class AddToCartTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
    }

    @Test
    void addToCartAndCheckTest() {
        String expectedProductName = driver.findElement(
                By.xpath("(//div[contains(@class, 'product-card')])[1]//h4")
        ).getText();

        driver.findElement(
                By.xpath("(//div[contains(@class, 'product-card')])[1]//button[@data-action='add-to-cart']")
        ).click();

        driver.findElement(By.id("open-cart-btn")).click();

        boolean isDisplayed = driver.findElement(
                By.xpath("//div[@id='cart-items']//*[contains(text(), '" + expectedProductName + "')]")
        ).isDisplayed();

        Assertions.assertThat(isDisplayed)
                .as("verification product")
                .isEqualTo(true);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
