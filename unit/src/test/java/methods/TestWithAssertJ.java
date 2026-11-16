package methods;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TestWithAssertJ {
    @Test
    void isEvenTest_ShouldReturnBoolean() {

        Random random = new Random();
        int n = random.nextInt(1, 101);

        boolean expectedResult = (n % 2 == 0);
        boolean actualResult = Task1.isEven(n);

        assertThat(actualResult)
                .as("Проверка числа " + n)
                .isEqualTo(expectedResult);
    }

    // Требование 2: Хотя бы один метод должен возвращать список.
    @Test
    void removeSpecificNameTest_ShouldReturnList() {
        List<String> inputList = Arrays.asList("Anna", "Oleg", "Anna", "Maria");

        String nameToRemove = "Anna";

        List<String> expectedResult = Arrays.asList("Oleg", "Maria");

        List<String> actualResult = Task12.removeSpecificName(inputList, nameToRemove);

        assertThat(actualResult)
                .as("Проверка списка после удаления имени " + nameToRemove)
                .containsExactlyElementsOf(expectedResult)
                .doesNotContain(nameToRemove);
    }

    @Test
    void isNegativeTest() {
        int n = 5;

        boolean expectedResult = false;

        boolean actualResult = Task3.isPositive(n);

        // 3. Проверка (Assert) - тест должен упасть
        assertThat(actualResult)
                .as("Демонстрация падения: проверка числа " + n + " на положительность")
                .isEqualTo(expectedResult);
}

    @RepeatedTest(3)
    void sumToTest() {
        Random random = new Random();
        int n = random.nextInt(1, 101);

        int expectedResult = 0;
        for (int i = 1; i <= n; i++) {
            expectedResult = expectedResult + i;
        }

        int actualResult = Task6.sumToN(n);

        assertThat(actualResult)
                .as("Проверка суммы чисел от 1 до " + n)
                .isEqualTo(expectedResult);
    }

}
