package lotto.utils;

import java.util.Set;

import lotto.domain.LottoNumber;

@FunctionalInterface
public interface LottoNumbersGenerator {
    Set<LottoNumber> generate(int size);
}
