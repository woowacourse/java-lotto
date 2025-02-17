package lotto.domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static lotto.constant.Limit.LOTTO_SIZE;
import static lotto.constant.Limit.MAX_LOTTO_NUMBER;

public class NumbersGenerator {
    private NumbersGenerator() {}

    public static Set<Integer> generateLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE.getValue()) {
            int number = new Random().nextInt(MAX_LOTTO_NUMBER.getValue() - 1) + 1;
            numbers.add(number);
        }
        return numbers;
    }
}
