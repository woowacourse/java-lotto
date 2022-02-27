package util;

import static domain.Lotto.LOTTO_NUMBER_MAXIMUM;
import static domain.Lotto.LOTTO_NUMBER_MINIMUM;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleNumberGenerator implements LottoNumberGenerator {

    private final List<Integer> candidateNumber = IntStream.range(LOTTO_NUMBER_MINIMUM, LOTTO_NUMBER_MAXIMUM + 1)
            .boxed()
            .collect(Collectors.toList());

    public List<Integer> generate() {
        Collections.shuffle(candidateNumber);
        return candidateNumber.stream()
                .limit(LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toList());
    }
}
