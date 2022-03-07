package lotto.model.result;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import lotto.model.money.Money;

public class LottoStatistics {

    private static final int DEFAULT_VALUE = 0;

    private final Map<LottoRank, Long> lottoRankCounter;

    public LottoStatistics(LottoRanks ranks) {
        this.lottoRankCounter = ranks.getLottoRanks().stream()
                .collect(Collectors
                        .groupingBy(Function.identity(), () -> new EnumMap<>(LottoRank.class), Collectors.counting()));
    }

    public long count(LottoRank rank) {
        if (lottoRankCounter.get(rank) != null) {
            return lottoRankCounter.get(rank);
        }
        return DEFAULT_VALUE;
    }

    public double calculateEarningRates(Money money) {
        long sum = lottoRankCounter.entrySet().stream()
                .mapToLong(calculateWinningPrize())
                .sum();
        return (double) sum / money.getAmount();
    }

    private ToLongFunction<Entry<LottoRank, Long>> calculateWinningPrize() {
        return entry -> entry.getKey().getPrize() * entry.getValue();
    }
}
