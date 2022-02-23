package lotto.util;

import lotto.model.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    public static final int LOTTO_START_NUMBER = 1;
    public static final int LOTTO_LAST_NUMBER = 45;
    public static final int LOTTO_START_INDEX = 0;
    public static final int LOTTO_LAST_INDEX = 6;

    public static Lotto generate() {
        List<Integer> numberRange = IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberRange);
        return new Lotto(numberRange.subList(LOTTO_START_INDEX, LOTTO_LAST_INDEX).stream()
                .sorted()
                .collect(Collectors.toList()));
    }
}
