package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;

import java.util.List;

public interface LottoGenerator {
    List<Lotto> generateLottosExceptManualGenerated(final int numberOfGenerating, final List<Lotto> manualGenerated);
}
