package lotto.domain;

import java.util.Set;

public interface LottoGenerator {
    int LOTTO_LENGTH = 6;
    Set<LottoNumber> generateNumbers();
}