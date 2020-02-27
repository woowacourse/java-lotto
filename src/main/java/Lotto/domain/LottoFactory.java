package Lotto.domain;

import Lotto.utils.AutoLottoNumberGenerator;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final AutoLottoNumberGenerator autoLottoNumberGenerator = new AutoLottoNumberGenerator();

    private LottoFactory() {
    }

    public static Lottos generateAutoLottos(int amount) {
        return new Lottos(IntStream.range(0, amount)
                .mapToObj(t -> generateAutoSingleLotto())
                .collect(Collectors.toList()));
    }

    private static Lotto generateAutoSingleLotto() {
        return new Lotto(autoLottoNumberGenerator.generate());
    }
}

