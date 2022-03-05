package lotto.model.prize;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lotto.model.Money;

public class PrizeCountMap {
    private final EnumMap<Prize, Long> prizeMap;

    public PrizeCountMap(EnumMap<Prize, Long> prizeMap) {
        this.prizeMap = new EnumMap<>(prizeMap);
    }

    public static PrizeCountMap from(List<MatchResult> matchResults) {
        EnumMap<Prize, Long> prizeMap = matchResults.stream()
                .collect(groupToEnumMapCollector());
        return new PrizeCountMap(prizeMap);
    }

    private static Collector<MatchResult, ?, EnumMap<Prize, Long>> groupToEnumMapCollector() {
        return Collectors.groupingBy(Prize::getPrize,
                () -> new EnumMap<>(Prize.class),
                Collectors.counting());
    }

    public double calculateEarningRate(Money money) {
        return money.rate(getTotalAmount());
    }

    private long getTotalAmount() {
        return prizeMap.keySet().stream()
                .mapToLong(prize -> prize.pickAmount(prizeMap.get(prize)))
                .sum();
    }

    public Long getCount(Prize prize) {
        if (prizeMap.get(prize) == null) {
            return 0L;
        }
        return prizeMap.get(prize);
    }
}
