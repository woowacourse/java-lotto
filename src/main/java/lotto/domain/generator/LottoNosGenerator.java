package lotto.domain.generator;

import lotto.domain.LottoNo;

import java.util.List;

@FunctionalInterface
public interface LottoNosGenerator {
    List<LottoNo> generate();
}
