package lotto.domain;

import static lotto.domain.LottoNumber.MAXIMUM_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MINIMUM_LOTTO_NUMBER;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int START_INDEX = 0;
    private static final int END_INDEX = 6;

    public List<LottoNumber> generate() {
        List<Integer> numbers = makeRandomNumbers();
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private List<Integer> makeRandomNumbers() {
        List<Integer> numberRange = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberRange);
        return numberRange.subList(START_INDEX, END_INDEX);
    }
}
