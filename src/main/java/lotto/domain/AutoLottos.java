package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.NullValidator.validateNull;

public class AutoLottos implements LottosGenerator {
    private final int autoLottosCount;

    public AutoLottos(LottoCount lottoCount) {
        validateNull(lottoCount);
        autoLottosCount = lottoCount.calculateAutoLottoCount();
    }

    public Lottos generate() {
        List<Lotto> autoLottos = IntStream.range(0, autoLottosCount)
                .mapToObj(index -> Lotto.create())
                .collect(Collectors.toList());
        return new Lottos(autoLottos);
    }
}
