package lotto.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private static final List<Integer> NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public List<Integer> generate() {
        Collections.shuffle(NUMBERS);
        List<Integer> numbers = NUMBERS.subList(0, 6);
        Collections.sort(numbers);
        return numbers;
    }
}
