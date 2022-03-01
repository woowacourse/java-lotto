package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.constants.NumberLimit;

public class LottoGenerator {

    private static final int START_INCLUSIVE = 0;
    private static final int END_EXCLUSIVE = 6;
    private static final List<LottoNumber> NUMBERS = initNumbers();

    private LottoGenerator() {
    }

    private static List<LottoNumber> initNumbers() {
        return IntStream.rangeClosed(NumberLimit.MINIMUM.getLimit(), NumberLimit.MAXIMUM.getLimit())
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static Lotto generate() {
        List<LottoNumber> numbers = subtractNumbers(shuffleNumbers());
        return new Lotto(numbers);
    }

    private static List<LottoNumber> shuffleNumbers() {
        List<LottoNumber> copiedNumbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(copiedNumbers);
        return copiedNumbers;
    }

    private static List<LottoNumber> subtractNumbers(List<LottoNumber> numbers) {
        return new ArrayList<>(numbers.subList(START_INCLUSIVE, END_EXCLUSIVE));
    }
}
