package domain.lotto.generator;

import domain.lotto.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private static final List<LottoNumber> numbers;
    private static final int LOTTO_UNDER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 6;

    static {
        numbers = IntStream.rangeClosed(LOTTO_UNDER_BOUND, LOTTO_UPPER_BOUND)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public SortedSet<LottoNumber> create() {
        Collections.shuffle(numbers);
        return new TreeSet<>(numbers.subList(START_INDEX, END_INDEX));
    }
}
