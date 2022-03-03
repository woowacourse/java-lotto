package lotto.domain;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class LottoResult {

    private static final int MATCH_RESULT_ADD_UNIT = 1;

    private final Map<Rank, Integer> rankResults;

    public LottoResult(final Map<Rank, Integer> rankResults) {
        this.rankResults = rankResults;
    }

    public static LottoResult createLottoResult(final Lottos lottos, final WinLotto winLotto) {
        final Map<Rank, Integer> rankResults = Rank.initResultMap();
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = winLotto.matchResult(lotto);
            rankResults.replace(rank, rankResults.get(rank) + MATCH_RESULT_ADD_UNIT);
        }
        return new LottoResult(rankResults);
    }

    public double calculateYield() {
        final long totalReward = calculateTotalReward();
        final int purchaseMoney = calculatePurchaseMoney();

        return totalReward / (double) purchaseMoney;
    }

    private Long calculateTotalReward() {
        return rankResults.entrySet().stream()
                .map(result -> Rank.calculateMoney(result.getKey(), result.getValue()))
                .reduce(0L, Long::sum);
    }

    private int calculatePurchaseMoney() {
        return rankResults.entrySet().stream()
                .map(Entry::getValue)
                .reduce(0, Integer::sum) * Lotto.LOTTO_PURCHASE_MONEY;
    }

    public Map<Rank, Integer> getRankResults() {
        return rankResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult that = (LottoResult) o;
        return Objects.equals(rankResults, that.rankResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankResults);
    }
}
