package lotto.model.prize;

import static java.util.stream.Collectors.summingInt;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lotto.model.Money;

/*
 * 모든 등수에 대한 당첨 정보를 가지는 일급 컬렉션 Class
 */
public class PrizeCountMap {
    private final EnumMap<Prize, Long> prizeMap;

    public PrizeCountMap(EnumMap<Prize, Long> prizeMap) {
        this.prizeMap = new EnumMap<>(prizeMap);
    }

    public static PrizeCountMap from(List<MatchResult> matchResults) {
        EnumMap<Prize, Long> prizeMap = matchResults.stream()
                .collect(getMatchResultEnumMapCollector());
        return new PrizeCountMap(prizeMap);
    }

    private static Collector<MatchResult, ?, EnumMap<Prize, Long>> getMatchResultEnumMapCollector() {
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
