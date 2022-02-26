package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LottoResult {

    private final Map<Rank, Integer> rankResults;

    public LottoResult(final Map<Rank, Integer> rankResults) {
        Objects.requireNonNull(rankResults, "[ERROR] LottoResult는 null로 생성할 수 없습니다.");
        this.rankResults = new LinkedHashMap<>(rankResults);
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
        return calculatePurchaseLottoCount() * Lotto.LOTTO_PURCHASE_MONEY;
    }

    private Integer calculatePurchaseLottoCount() {
        return rankResults.values().stream()
                .reduce(0, Integer::sum);
    }

    public Map<Rank, Integer> getRankResults() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(rankResults));
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
