package lotto.domain.lottogenerator;

import lotto.domain.LottoNumber;

import java.util.List;

public interface LottoGeneratingStrategy {
    List<LottoNumber> generate();
}
