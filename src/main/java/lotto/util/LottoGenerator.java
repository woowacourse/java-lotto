package lotto.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGenerator {
    private static final int START = 0;
    private static final int END = 6;
    private static final int MAXIMUM_CANDIDATE_NUMBER = 45;

    private LottoGenerator() {
    }

    public static List<Integer> makeLottoNumbers() {
        List<Integer> numbers = Stream.iterate(1, n -> n + 1)
                .limit(MAXIMUM_CANDIDATE_NUMBER)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        List<Integer> lottoNumber = numbers.subList(START, END);
        Collections.sort(lottoNumber);

        return lottoNumber;
    }
}
