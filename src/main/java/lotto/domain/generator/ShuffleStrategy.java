package lotto.domain.generator;

import lotto.domain.LottoNo;

import java.util.List;

@FunctionalInterface
public interface ShuffleStrategy {
    List<LottoNo> shuffle(List<LottoNo> lottoNos);
}
