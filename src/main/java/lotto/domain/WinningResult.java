package lotto.domain;

import java.util.Map;
import java.util.stream.IntStream;

public class WinningResult {
    public static double getTotalYield(Map<Rank, Integer> matchedRankCount, Integer purchasePrice) {
        return getTotalRevenue(matchedRankCount) / Double.valueOf(purchasePrice);
    }

    private static double getTotalRevenue(Map<Rank, Integer> matchedRankCount) {
        return IntStream.rangeClosed(0, 6).mapToObj(number -> {
            Rank rank = Rank.valueOf(number);
            return rank.getTotalRankWinningMoney(matchedRankCount.get(rank));
        }).mapToInt(Integer::intValue).sum();
    }
}
