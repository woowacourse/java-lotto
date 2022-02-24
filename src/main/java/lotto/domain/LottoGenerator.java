package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.constants.NumberLimit;
import lotto.domain.vo.Number;

public class LottoGenerator {

    private static final int START_INCLUSIVE = 0;
    private static final int END_EXCLUSIVE = 6;

    private final List<Number> numbers;

    public LottoGenerator() {
        this.numbers = initNumbers();
    }

    private List<Number> initNumbers() {
        return IntStream.rangeClosed(NumberLimit.MINIMUM.getLimit(), NumberLimit.MAXIMUM.getLimit())
                .mapToObj(Number::new)
                .collect(Collectors.toList());
    }

    public Lotto generate() {
        Collections.shuffle(numbers);
        return new Lotto(new ArrayList<>(numbers.subList(START_INCLUSIVE, END_EXCLUSIVE)));
    }
}
