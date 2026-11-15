package methods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1 {

    @BeforeEach
    void before() {
        System.out.println("=========================Test method start");
    }

    @AfterEach
    void after() {
        System.out.println("Test method end");
        System.out.println("=========================");
    }

    @Test
    void isEvenTest() {
        Random random = new Random();
        int n = random.nextInt(1, 101);

        boolean result = Task1.isEven(n);

        System.out.println("Method: isEven, n = " + n + ", result = " + result);
    }

    @RepeatedTest(20)
    void checkAccessTest() {
        Random random = new Random();
        int age = random.nextInt(0, 100);

        String result = Task2.checkAccess(age);

        System.out.println("Method: checkAccess, age = " + age + ", result = " + result);
    }

    static List<Integer> randomScores() {
        Random random = new Random();
        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            scores.add(random.nextInt(0, 101));
        }

        return scores;
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    void getGradeTest(int score) {
        String result = Task4.getGrade(score);

        System.out.println("Method: getGrade, score = " + score + ", result = " + result);
    }
}