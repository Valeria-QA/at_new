package methods;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test2 {

    @Test
    void isEvenTest() {
        Random random = new Random();
        int n = random.nextInt(1, 101);

        boolean expectedResult = (n % 2 == 0);
        boolean actualResult = Task1.isEven(n);

        System.out.println("isEven, n = " + n);
        if (actualResult == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void checkAccessTest() {
        Random random = new Random();
        int age = random.nextInt(0, 100);

        String expectedResult;
        if (age > 18) {
            expectedResult = "Allowed";
        } else {
            expectedResult = "Denied";
        }
        String actualResult = Task2.checkAccess(age);

        System.out.println("checkAccess, age = " + age);
        if (actualResult.equals(expectedResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void isPositiveTest() {
        Random random = new Random();
        int n = random.nextInt(-150, 151);

        boolean expectedResult = (n >= 0);
        boolean actualResult = Task3.isPositive(n);

        System.out.println("isPositive, n = " + n);
        if (actualResult == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void getGradeTest() {
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

        System.out.println("getGrade, score = " + score);
        if (actualResult.equals(expectedResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(3)
    void blastOffTest() {
        Random random = new Random();
        int start = random.nextInt(1, 11);

        String expectedResult = "";
        for (int i = start; i >= 1; i--) {
            expectedResult = expectedResult + i + " ";
        }
        expectedResult = expectedResult + "Поехали!";

        String actualResult = Task5.blastOff(start);

        System.out.println("blastOff, start = " + start);
        if (actualResult.equals(expectedResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(3)
    void sumToNTest() {
        Random random = new Random();
        int n = random.nextInt(1, 101);

        int expectedResult = 0;
        for (int i = 1; i <= n; i++) {
            expectedResult = expectedResult + i;
        }

        int actualResult = Task6.sumToN(n);

        System.out.println("sumToN, n = " + n);
        if (actualResult == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(3)
    void getEvenInRangeTest() {
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

        System.out.println("getEvenInRange, start = " + start + ", end = " + end);
        if (actualResult.equals(expectedResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @RepeatedTest(3)
    void findMaxTest() {
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

        System.out.println("findMax, expected = " + expectedResult);
        if (actualResult == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    static List<Integer> randomSizes() {
        Random random = new Random();
        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            sizes.add(random.nextInt(1, 6));
        }

        return sizes;
    }

    @ParameterizedTest
    @MethodSource("randomSizes")
    void hasBugTest(int size) {
        Random random = new Random();
        String[] words = {"Ok", "Warning", "Error", "Bug", "bug", "Info"};
        String[] messages = new String[size];

        boolean expectedResult = false;
        for (int i = 0; i < size; i++) {
            messages[i] = words[random.nextInt(0, words.length)];
            if (messages[i].equalsIgnoreCase("Bug")) {
                expectedResult = true;
            }
        }

        boolean actualResult = Task7.hasBug(messages);

        System.out.println("hasBug, size = " + size);
        if (actualResult == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @ParameterizedTest
    @MethodSource("randomSizes")
    void reverseTest(int size) {
        Random random = new Random();
        String[] words = {"One", "Two", "Three", "Four", "Five"};
        String[] arr = new String[size];
        String[] expectedResult = new String[size];

        for (int i = 0; i < size; i++) {
            arr[i] = words[random.nextInt(0, words.length)];
        }
        for (int i = 0; i < size; i++) {
            expectedResult[i] = arr[size - 1 - i];
        }

        String[] actualResult = Task10.reverse(arr);

        boolean same = true;
        for (int i = 0; i < size; i++) {
            if (!actualResult[i].equals(expectedResult[i])) {
                same = false;
            }
        }

        System.out.println("reverse, size = " + size);
        if (same) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @ParameterizedTest
    @MethodSource("randomSizes")
    void calcAverageTest(int size) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < size; i++) {
            int number = random.nextInt(1, 101);
            list.add(number);
            sum = sum + number;
        }

        int expectedResult = sum / size;
        int actualResult = Task11.calcAverage(list);

        System.out.println("calcAverage, size = " + size);
        if (actualResult == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @ParameterizedTest
    @MethodSource("randomSizes")
    void removeSpecificNameTest(int size) {
        Random random = new Random();
        String[] names = {"Anna", "Ivan", "Olga"};
        String nameToRemove = "Ivan";
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

        System.out.println("removeSpecificName, size = " + size);
        if (actualResult.equals(expectedResult)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }
}
