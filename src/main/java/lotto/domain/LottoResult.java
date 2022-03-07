package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_COUNT = 0;

    private final Map<Rank, Integer> lottoResult = new HashMap<>();

    public LottoResult() {
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, DEFAULT_COUNT);
        }
    }

    public Map<Rank, Integer> getLottoResult() {
        return Map.copyOf(lottoResult);
    }

    public void increaseRankCount(final Rank rank) {
        lottoResult.put(rank, lottoResult.get(rank) + 1);
    }

    public double calculateRate(final Payment payment) {
        int money = payment.getPayment();
        long totalMoney = getTotalMoney();
        return (double) totalMoney / money;
    }

    private long getTotalMoney() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> (long) rank.getMoney() * lottoResult.get(rank))
                .sum();
    }
}
