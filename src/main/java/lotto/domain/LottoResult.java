package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final EnumMap<Rank, Integer> lottoResult;

    public LottoResult(Map<Rank, Integer> lottoResult) {
        this.lottoResult = new EnumMap<>(lottoResult);
    }

    public int calculateTotalPrize() {
        return Rank.calculateTotalPrize(lottoResult);
    }

    public EnumMap<Rank, Integer> getResult() {
        return lottoResult;
    }
}
