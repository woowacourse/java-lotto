package lotto.domain.generator;

import lotto.domain.LottoNumbers;

import java.util.List;

public interface Generator {
    List<LottoNumbers> generateLottoNumbersGroup(final int numberOfGenerating);
}
