package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_COUNT = 0;
    private static final int CHECK_BONUS_COUNT = 5;

    private final Map<Rank, Integer> lottoResult = new HashMap<>();

    public LottoResult() {
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, DEFAULT_COUNT);
        }
    }

    public Map<Rank, Integer> getLottoResult() {
        return Map.copyOf(lottoResult);
    }

    public int getTotalMoney() {
        int totalMoney = 0;
        for (Rank rank : Rank.values()) {
            totalMoney += rank.getMoney() * lottoResult.get(rank);
        }
        return totalMoney;
    }

    public void increaseRankCount(final Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank) + 1);
    }

    public double calculateRate(final int totalMoney, final Payment payment) {
        int money = payment.getPayment();
        return (double) totalMoney / money;
    }
}
