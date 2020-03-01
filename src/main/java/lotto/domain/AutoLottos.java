package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.NullValidator.validateNull;

public class AutoLottos implements LottosGenerator {

    public Lottos generate(LottoCount lottoCount) {
        validateNull(lottoCount);

        int autoLottosCount = lottoCount.calculateAutoLottoCount();
        List<Lotto> autoLottos = IntStream.range(0, autoLottosCount)
                .mapToObj(index -> Lotto.create())
                .collect(Collectors.toList());
        return new Lottos(autoLottos);
    }
}
