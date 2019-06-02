package lotto.domain.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.lotto.Number;

public class RandomNumbersGenerator {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private static final List<Number> numberList;

    static {
        numberList = new ArrayList<>();
        IntStream.range(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEach(i -> numberList.add(Number.of(i)));
    }

    private static List<Number> generateRandomNumbers() {
        Collections.shuffle(numberList);
         return numberList.stream().limit(6).collect(Collectors.toList());
    }

    public static List<Number> create() {
        return RandomNumbersGenerator.generateRandomNumbers();
    }
}
