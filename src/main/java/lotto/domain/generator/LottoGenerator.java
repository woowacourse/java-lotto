package lotto.domain.generator;

import lotto.domain.lottonumber.LottoNumbers;

import java.util.List;

public interface LottoGenerator {
    List<LottoNumbers> generateLottoNumbersGroup(final int numberOfGenerating);
}
