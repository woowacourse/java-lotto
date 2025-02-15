package lotto.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.MAX_LOTTO_NUMBER;

public class NumbersGenerator {
    private NumbersGenerator() {}

    public static Set<Integer> generateLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE) {
            int number = new Random().nextInt(MAX_LOTTO_NUMBER - 1) + 1;
            numbers.add(number);
        }
        return numbers;
    }
}
