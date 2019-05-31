package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private LottoMachine() {
        throw new AssertionError();
    }

    public static Lottos buyAutoLottos(final int numberOfLottos) {
        Lottos lottos = new Lottos();
        for (int i = 0; i < numberOfLottos; i++) {
            lottos.add(LottoNumbersGenerator.getLottoNumbers());
        }
        return lottos;
    }

    public static Lottos buyManualLotto(final List<LottoNumbers> lottosNumbers) {
        return new Lottos(lottosNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList()));
    }
}
