package lotto.util;

import lotto.model.Lotto;
import lotto.model.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private static final int LOTTO_START_INDEX = 0;
    private static final int LOTTO_LAST_INDEX = 6;

    private static final List<Integer> numberRange = IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER)
        .boxed()
        .collect(Collectors.toList());

    private RandomLottoGenerator() {
    }

    public static Lotto generateAutoLotto() {
        Collections.shuffle(numberRange);
        return new Lotto(numberRange.subList(LOTTO_START_INDEX, LOTTO_LAST_INDEX).stream()
            .sorted()
            .map(LottoNumber::new)
            .collect(Collectors.toList()));
    }
}
