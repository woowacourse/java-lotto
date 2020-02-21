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
    private static final int START_LOTTO_INCLUSIVE = 1;
    private static final int END_LOTTO_EXCLUSIVE = 46;
    private static final int START_INDEX = 0;
    private static final int END_INDEX = 6;

    static {
        numbers = IntStream.range(START_LOTTO_INCLUSIVE, END_LOTTO_EXCLUSIVE)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @Override
    public SortedSet<LottoNumber> create() {
        Collections.shuffle(numbers);
        return new TreeSet<>(numbers.subList(START_INDEX, END_INDEX));
    }
}
