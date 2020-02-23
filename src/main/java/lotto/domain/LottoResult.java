package lotto.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class LottoResult {

    private final Map<LottoRank, Integer> rankResult;

    public LottoResult(Map<LottoRank, Integer> rankResult) {
        this.rankResult = Collections.unmodifiableMap(rankResult);
    }

    public long calculateTotalPrize() {
        Set<LottoRank> lottoRanks = rankResult.keySet();
        return lottoRanks.stream()
            .mapToLong(lottoRank -> lottoRank.getTotal(rankResult.get(lottoRank)))
            .sum();
    }

    public Map<LottoRank, Integer> getRankResult() {
        return rankResult;
    }
}
