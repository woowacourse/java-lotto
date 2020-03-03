package lotto.generator;

import lotto.domain.LottoNumber;

import java.util.List;

public interface NumberGenerator {
    List<LottoNumber> generateNumbers();

    List<LottoNumber> generateNumbers(final String Input);
}
