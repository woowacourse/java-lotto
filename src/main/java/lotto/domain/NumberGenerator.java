package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {
    private static Random random = new Random();

    static List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(44) + 1;
            numbers.add(number);
        }
        return numbers;
    }
}
