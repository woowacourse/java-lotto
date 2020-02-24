package lotto.generator;

import java.util.Set;

import lotto.domain.LottoNumber;

public interface LottoNumberGenerator {
    abstract Set<LottoNumber> create();
}
