package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;

import java.util.List;

public interface LottoGenerator {
    List<Lotto> generateLottos(final int numberOfGenerating);
}
