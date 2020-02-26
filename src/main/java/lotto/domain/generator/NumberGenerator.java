package lotto.domain.generator;

import lotto.domain.LottoNumber;

import java.util.List;

public interface NumberGenerator {
    List<LottoNumber> generateNumbers();

    List<LottoNumber> generateNumbers(String Input);
}
