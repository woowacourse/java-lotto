package lotto.domain.generator;

import lotto.domain.LottoNumber;

import java.util.List;

@FunctionalInterface
public interface LottoNumberGenerator {
    int LOTTO_TO_INDEX = 6;
    int LOTTO_FROM_INDEX = 0;

    List<LottoNumber> generate();
}
