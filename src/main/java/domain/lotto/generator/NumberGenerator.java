package domain.lotto.generator;

import domain.lotto.LottoNumber;

import java.util.SortedSet;

public interface NumberGenerator {
    SortedSet<LottoNumber> create();
}
