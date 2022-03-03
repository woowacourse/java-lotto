package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> lottoResult;

    public LottoResult(final Map<Rank, Integer> lottoResult) {
        this.lottoResult = new HashMap<>(lottoResult);
    }

    public Money getProfit() {
        return Rank.getTotalWinningPrize(lottoResult);
    }

    public Map<Rank, Integer> getLottoResult() {
        return Map.copyOf(lottoResult);
    }
}
