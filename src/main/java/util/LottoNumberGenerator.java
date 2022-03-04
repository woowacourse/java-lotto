package util;

import static domain.Lotto.LOTTO_NUMBER_MAXIMUM;
import static domain.Lotto.LOTTO_NUMBER_MINIMUM;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class LottoNumberGenerator implements NumberGenerator {

    public static final int LOTTO_NUMBER_LENGTH = 6;

    protected List<Integer> CANDIDATE_NUMBERS = IntStream.range(LOTTO_NUMBER_MINIMUM, LOTTO_NUMBER_MAXIMUM + 1)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public List<Integer> generate() {
        Collections.shuffle(CANDIDATE_NUMBERS);

        return CANDIDATE_NUMBERS.stream()
                .limit(LOTTO_NUMBER_LENGTH)
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }
}
