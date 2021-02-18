package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private static final List<Integer> NUMBERS = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;

    @Override
    public List<Integer> generate() {
        Collections.shuffle(NUMBERS);
        List<Integer> numbers = NUMBERS.subList(FROM_INDEX, TO_INDEX);
        numbers.sort(Comparator.naturalOrder());
        return new ArrayList<>(numbers);
    }
}
