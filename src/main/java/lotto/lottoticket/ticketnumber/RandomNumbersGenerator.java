package lotto.lottoticket.ticketnumber;

import lotto.lottoticket.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumbersGenerator implements NumbersGenerator {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    public static final int NUMBER_COUNT_IN_LOTTO = 6;

    private static final List<Integer> NUMBERS_IN_RANGE =
            IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                    .boxed()
                    .collect(Collectors.toList());

    @Override
    public List<Number> generate() {
        List<Integer> randomNumbers = new ArrayList<>();
        Collections.shuffle(NUMBERS_IN_RANGE);
        for (int i = 0; i < NUMBER_COUNT_IN_LOTTO; i++) {
            randomNumbers.add(NUMBERS_IN_RANGE.get(i));
        }
        Collections.sort(randomNumbers);
        return randomNumbers.stream()
                .map(n -> Integer.toString(n))
                .map(Number::new)
                .collect(Collectors.toList());
    }
}
