package Lotto.utils;

import Lotto.domain.Lotto;
import Lotto.domain.LottoCount;
import Lotto.domain.LottoNumber;
import Lotto.domain.Lottos;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottosGenerator implements LottosGenerator {
    private static final int MIN_LOTTO_NUMBER_START = 1;
    private static final int MAX_LOTTO_NUMBER_END_EXCLUDED = 46;
    private static final int LOTTO_NUMBER_SIZE = 6;

    private LottoCount count;

    public AutoLottosGenerator(LottoCount count) {
        this.count = count;
    }

    @Override
    public Lottos generate() {
        return new Lottos(IntStream.range(0, count.getLottoCount())
                .mapToObj(t -> new Lotto(autoSingleGenerate()))
                .collect(Collectors.toList()));
    }

    private List<LottoNumber> autoSingleGenerate() {
        return ThreadLocalRandom.current()
                .ints(MIN_LOTTO_NUMBER_START, MAX_LOTTO_NUMBER_END_EXCLUDED)
                .distinct()
                .limit(LOTTO_NUMBER_SIZE)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
