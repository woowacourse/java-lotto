package Lotto.utils;

import Lotto.domain.Lotto;
import Lotto.domain.LottoCount;
import Lotto.domain.Lottos;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottosGenerator implements LottosGenerator {
    private static final AutoLottoNumberGenerator autoLottoNumberGenerator = new AutoLottoNumberGenerator();
    private static final String NULL = null;

    private LottoCount count;

    public AutoLottosGenerator(LottoCount count) {
        this.count = count;
    }

    @Override
    public Lottos generate() {
        return new Lottos(IntStream.range(0, count.getLottoCount())
                .mapToObj(t -> new Lotto(autoLottoNumberGenerator.generate(NULL)))
                .collect(Collectors.toList()));
    }
}
