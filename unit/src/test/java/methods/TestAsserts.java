package methods;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAsserts {
    @Tag("smoke")
    @RepeatedTest(10)
    void isEvenTest_ShouldReturnBoolean() {
        Random random = new Random();
        int n = random.nextInt(1, 101);

        boolean expectedResult = (n % 2 == 0);
        boolean actualResult = Task1.isEven(n);

        assertThat(actualResult)
                .as("Проверка isEven для числа " + n)
                .isEqualTo(expectedResult);
    }

    @Tag("smoke")
    @RepeatedTest(10)
    void checkAccessTest_ShouldReturnString() {
        Random random = new Random();
        int age = random.nextInt(0, 100);

        String expectedResult;
        if (age > 18) {
            expectedResult = "Allowed";
        } else {
            expectedResult = "Denied";
        }

        String actualResult = Task2.checkAccess(age);

        assertThat(actualResult)
                .as("Проверка checkAccess для возраста " + age)
                .isEqualTo(expectedResult);
    }

    @Tag("smoke")
    @RepeatedTest(10)
    void isPositiveTest_ShouldReturnBoolean() {
        Random random = new Random();
        int n = random.nextInt(-150, 151);

        boolean expectedResult = (n >= 0);
        boolean actualResult = Task3.isPositive(n);

        assertThat(actualResult)
                .as("Проверка isPositive для числа " + n)
                .isEqualTo(expectedResult);
    }

    @Tag("regress")
    @RepeatedTest(10)
    void getGradeTest_ShouldReturnString() {
        Random random = new Random();
        int score = random.nextInt(-50, 151);

        String expectedResult;
        if (score < 0) {
            expectedResult = "Error";
        } else if (score <= 20) {
            expectedResult = "E";
        } else if (score <= 40) {
            expectedResult = "D";
        } else if (score <= 60) {
            expectedResult = "C";
        } else if (score <= 80) {
            expectedResult = "B";
        } else if (score <= 100) {
            expectedResult = "A";
        } else {
            expectedResult = "Error";
        }

        String actualResult = Task4.getGrade(score);

        assertThat(actualResult)
                .as("Проверка getGrade для балла " + score)
                .isEqualTo(expectedResult);
    }

    @Tag("regress")
    @RepeatedTest(10)
    void blastOffTest_ShouldReturnString() {
        Random random = new Random();
        int start = random.nextInt(1, 11);

        String expectedResult = "";
        for (int i = start; i >= 1; i--) {
            expectedResult = expectedResult + i + " ";
        }
        expectedResult = expectedResult + "Поехали!";

        String actualResult = Task5.blastOff(start);

        assertThat(actualResult)
                .as("Проверка blastOff для start " + start)
                .isEqualTo(expectedResult);
    }

    @Tag("regress")
    @RepeatedTest(10)
    void sumToNTest_ShouldReturnInt() {
        Random random = new Random();
        int n = random.nextInt(1, 101);

        int expectedResult = n * (n + 1) / 2;
        int actualResult = Task6.sumToN(n);

        assertThat(actualResult)
                .as("Проверка sumToN для n " + n)
                .isEqualTo(expectedResult);
    }

    @Tag("new")
    @RepeatedTest(10)
    void hasBugTest_ShouldReturnBoolean() {
        Random random = new Random();
        boolean expectedResult = random.nextBoolean();

        List<String> list = List.of("Ok", "Warning", "Info");
        List<String> params = new ArrayList<>();
        for (int i = 0; i < random.nextInt(list.size()); i++) {
            params.add(list.get(i));
        }
        if (expectedResult) {
            params.add("Bug");
        }

        String[] messages = params.toArray(new String[0]);

        boolean actualResult = Task7.hasBug(messages);

        assertThat(actualResult)
                .as("Проверка hasBug для списка " + params)
                .isEqualTo(expectedResult);
    }

    @Tag("new")
    @RepeatedTest(10)
    void getEvenInRangeTest_ShouldReturnString() {
        Random random = new Random();
        int start = random.nextInt(1, 11);
        int end = random.nextInt(10, 21);

        String expectedResult = "";
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (expectedResult.equals("")) {
                    expectedResult = "" + i;
                } else {
                    expectedResult = expectedResult + " " + i;
                }
            }
        }

        String actualResult = Task8.getEvenInRange(start, end);

        assertThat(actualResult)
                .as("Проверка getEvenInRange для " + start + " и " + end)
                .isEqualTo(expectedResult);
    }

    @Tag("new")
    @RepeatedTest(10)
    void findMaxTest_ShouldReturnInt() {
        Random random = new Random();
        int[] arr = new int[5];
        int expectedResult = -1000;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(-100, 101);
            if (arr[i] > expectedResult) {
                expectedResult = arr[i];
            }
        }

        int actualResult = Task9.findMax(arr);

        assertThat(actualResult)
                .as("Проверка findMax, ожидаемый максимум " + expectedResult)
                .isEqualTo(expectedResult);
    }

    @Tag("new")
    @RepeatedTest(10)
    void reverseTest_ShouldReturnArray() {
        Random random = new Random();
        String[] words = {"One", "Two", "Three", "Four", "Five"};
        int size = random.nextInt(1, 6);

        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = words[random.nextInt(0, words.length)];
        }

        String[] expectedResult = new String[size];
        for (int i = 0; i < size; i++) {
            expectedResult[i] = arr[size - 1 - i];
        }

        String[] actualResult = Task10.reverse(arr);

        assertThat(actualResult)
                .as("Проверка reverse для массива размера " + size)
                .containsExactly(expectedResult);
    }

    @Tag("regress")
    @RepeatedTest(10)
    void calcAverageTest_ShouldReturnInt() {
        Random random = new Random();
        int size = random.nextInt(1, 6);

        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(1, 101);
            list.add(number);
            sum = sum + number;
        }

        int expectedResult = sum / size;
        int actualResult = Task11.calcAverage(list);

        assertThat(actualResult)
                .as("Проверка calcAverage для списка " + list)
                .isEqualTo(expectedResult);
    }

    @Tag("regress")
    @RepeatedTest(10)
    void removeSpecificNameTest_ShouldReturnList() {
        Random random = new Random();
        String[] names = {"Anna", "Ivan", "Olga"};
        String nameToRemove = "Ivan";
        int size = random.nextInt(1, 7);

        List<String> list = new ArrayList<>();
        List<String> expectedResult = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String name = names[random.nextInt(0, names.length)];
            list.add(name);
            if (!name.equals(nameToRemove)) {
                expectedResult.add(name);
            }
        }

        List<String> actualResult = Task12.removeSpecificName(list, nameToRemove);

        assertThat(actualResult)
                .as("Проверка removeSpecificName для списка " + list)
                .containsExactlyElementsOf(expectedResult)
                .doesNotContain(nameToRemove);
    }
}
