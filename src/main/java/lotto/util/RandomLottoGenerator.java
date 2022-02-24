package lotto.util;

import lotto.model.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private static final int LOTTO_START_INDEX = 0;
    private static final int LOTTO_LAST_INDEX = 6;

    private static List<Integer> numberCollection;

    public static Lotto generate() {
        numberCollection = IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberCollection);
        return new Lotto(numberCollection.subList(LOTTO_START_INDEX, LOTTO_LAST_INDEX).stream()
                .sorted()
                .collect(Collectors.toList()));
    }
}
