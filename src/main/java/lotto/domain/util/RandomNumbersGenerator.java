package lotto.domain.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import lotto.domain.lotto.Number;

public class RandomNumbersGenerator {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final List<Number> numberList;

    private RandomNumbersGenerator() {
        numberList = new ArrayList<>();
        IntStream.range(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEach(i -> numberList.add(new Number(i)));
    }

    private List<Number> generateRandomNumbers() {
        Collections.shuffle(numberList);
        List<Number> result = numberList.subList(0, 6);
        Collections.sort(result);
        return result;
    }

    public static List<Number> create() {
        return new RandomNumbersGenerator().generateRandomNumbers();
    }
}
