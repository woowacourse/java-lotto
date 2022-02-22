package lotto.domain;

import java.util.Map;
import java.util.Map.Entry;

public class LottoResult {

    private final Map<Rank, Integer> rankResults;

    public LottoResult(final Map<Rank, Integer> rankResults) {
        this.rankResults = rankResults;
    }

    public double calculateYield() {
        final long totalReward = rankResults.entrySet().stream()
                .map(result -> Rank.calculateMoney(result.getKey(), result.getValue()))
                .reduce(0L, Long::sum);

        final int purchaseMoney = rankResults.entrySet().stream()
                .map(Entry::getValue)
                .reduce(0, Integer::sum) * Lotto.LOTTO_PURCHASE_MONEY;

        return totalReward / (double) purchaseMoney;
    }
}
