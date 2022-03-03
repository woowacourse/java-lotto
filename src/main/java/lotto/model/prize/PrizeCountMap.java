package lotto.model.prize;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import lotto.model.Money;

/*
 * 모든 등수에 대한 당첨 정보를 가지는 일급 컬렉션 Class
 */
public class PrizeCountMap {
    private final EnumMap<Prize, Integer> prizeMap;

    public PrizeCountMap(EnumMap<Prize, Integer> prizeMap) {
        this.prizeMap = new EnumMap<>(prizeMap);
    }

    public static PrizeCountMap from(List<MatchResult> matchResults) {
        EnumMap<Prize, Integer> prizeMap = new EnumMap<>(Prize.class);
        Arrays.stream(Prize.values())
                .filter(prize -> prize != Prize.NONE)
                .forEach(prize -> prizeMap.put(prize, count(matchResults, prize)));
        return new PrizeCountMap(prizeMap);
    }

    private static int count(List<MatchResult> matchResults, Prize prize) {
        return (int) matchResults.stream()
                .filter(matchResult -> Prize.getPrize(matchResult) == prize)
                .count();
    }

    public double calculateEarningRate(Money money) {
        return money.rate(getTotalAmount());
    }

    private long getTotalAmount() {
        return prizeMap.keySet().stream()
                .mapToLong(prize -> prize.pickAmount(prizeMap.get(prize)))
                .sum();
    }

    public EnumMap<Prize, Integer> getPrizeMap() {
        return prizeMap;
    }
}
