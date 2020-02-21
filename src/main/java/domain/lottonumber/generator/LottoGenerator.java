package domain.lottonumber.generator;

import domain.lottonumber.LottoNumber;

import java.util.SortedSet;

public interface LottoGenerator {
    SortedSet<LottoNumber> create();
}
