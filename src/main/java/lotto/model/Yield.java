package lotto.model;

import java.util.Map;

public class Yield {
    private static final int GAIN_THRESHOLD = 1;

    private final float yield;

    Yield(LottoMoney lottoMoney, Map<Rank, Long> result) {
        this.yield = calculateYield(lottoMoney, result);
    }

    private float calculateYield(LottoMoney lottoMoney, Map<Rank, Long> result) {
        return getTotalWinningMoney(result) / (float)lottoMoney.getLottoMoney();
    }

    long getTotalWinningMoney(Map<Rank, Long> result) {
        return result.entrySet().stream()
            .map(entry -> entry.getKey().getMoney() * entry.getValue())
            .mapToLong(i -> i)
            .sum();
    }

    public boolean isGain() {
        return yield >= GAIN_THRESHOLD;
    }

    public float getYield() {
        return yield;
    }
}
