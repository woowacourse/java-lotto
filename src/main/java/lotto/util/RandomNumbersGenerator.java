package lotto.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.LottoNumber;

public class RandomNumbersGenerator implements RandomUtil {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_LAST_NUMBER = 45;
    private static final int LOTTO_START_INDEX = 0;
    private static final int LOTTO_LAST_INDEX = 6;

    private static List<Integer> numberCollection;

    public List<LottoNumber> generate() {
        numberCollection = IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberCollection);
        return numberCollection.subList(LOTTO_START_INDEX, LOTTO_LAST_INDEX).stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
