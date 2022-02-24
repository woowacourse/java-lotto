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
    private static final List<Number> NUMBERS = initNumbers();

    private LottoGenerator() {
    }

    private static List<Number> initNumbers() {
        return IntStream.rangeClosed(NumberLimit.MINIMUM.getLimit(), NumberLimit.MAXIMUM.getLimit())
                .mapToObj(Number::new)
                .collect(Collectors.toList());
    }

    public static Lotto generate() {
        Collections.shuffle(NUMBERS);
        return new Lotto(new ArrayList<>(NUMBERS.subList(START_INCLUSIVE, END_EXCLUSIVE)));
    }
}
