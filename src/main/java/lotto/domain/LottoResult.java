package lotto.domain;

import java.util.EnumMap;

public class LottoResult {

    private final EnumMap<Rank, Integer> statistics;
    private final double yield;

    public LottoResult(EnumMap<Rank, Integer> statistics, double yield) {
        this.statistics = statistics;
        this.yield = yield;
    }

    public EnumMap<Rank, Integer> getStatistics() {
        return statistics;
    }

    public double getYield() {
        return yield;
    }
}
