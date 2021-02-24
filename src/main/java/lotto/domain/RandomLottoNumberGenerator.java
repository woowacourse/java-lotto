package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private static final List<Integer> NUMBERS = IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public List<Integer> generate() {
        Collections.shuffle(NUMBERS);
        List<Integer> numbers = NUMBERS.subList(0, 6);
        return numbers;
    }
}
