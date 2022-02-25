package util;

import domain.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleNumberGenerator implements LottoNumberGenerator {

    private static final int LOTTO_NUMBER_MINIMUM = 1;
    private static final int LOTTO_NUMBER_MAXIMUM = 45;
    private static final int LOTTO_NUMBER_LENGTH = 6;

    private final List<Integer> candidateNumber = IntStream.range(LOTTO_NUMBER_MINIMUM, LOTTO_NUMBER_MAXIMUM + 1)
            .boxed()
            .collect(Collectors.toList());

    public List<LottoNumber> generate() {
        Collections.shuffle(candidateNumber);
        return candidateNumber.stream()
                .limit(LOTTO_NUMBER_LENGTH)
                .sorted()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
