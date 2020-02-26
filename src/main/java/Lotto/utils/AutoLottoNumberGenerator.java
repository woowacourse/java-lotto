package Lotto.utils;

import Lotto.domain.Lotto;
import Lotto.domain.LottoNumber;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class AutoLottoNumberGenerator implements NumberGenerator {
    private static final int MIN_LOTTO_NUMBER_START = 1;
    private static final int MAX_LOTTO_NUMBER_END_EXCLUDED = 46;
    private static final int LOTTO_NUMBER_SIZE = 6;

    @Override
    public List<LottoNumber> generate() {
        return ThreadLocalRandom.current()
                .ints(MIN_LOTTO_NUMBER_START, MAX_LOTTO_NUMBER_END_EXCLUDED)
                .distinct()
                .limit(LOTTO_NUMBER_SIZE)
                .mapToObj((t) -> new LottoNumber(t))
                .collect(Collectors.toList());
    }
}
