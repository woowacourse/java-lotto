package domain.lotto.generator;

import domain.lotto.LottoNumber;

import java.util.List;

public interface NumberGenerator {
    List<LottoNumber> create();
}
