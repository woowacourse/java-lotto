package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<MatchRank, Integer> rankCounts;
    private final long totalPrize;

    private LottoStatistics(Map<MatchRank, Integer> rankCounts, long totalPrize) {
        this.rankCounts = Collections.unmodifiableMap(rankCounts);
        this.totalPrize = totalPrize;
    }

    public static LottoStatistics from(Wallet wallet, WinningInform winningInform) {
        List<MatchResult> results = wallet.getMatchResults(winningInform);
        Map<MatchRank, Integer> rankCounts = calculateRankCounts(results);
        long totalPrize = calculateTotalPrize(rankCounts);
        return new LottoStatistics(rankCounts, totalPrize);
    }

    private static Map<MatchRank, Integer> calculateRankCounts(List<MatchResult> results) {
        Map<MatchRank, Integer> counts = new EnumMap<>(MatchRank.class);
        for (MatchRank rank : MatchRank.values()) {
            counts.put(rank, 0);
        }

        for (MatchResult result : results) {
            MatchRank rank = MatchRank.getMatchRank(result);
            counts.put(rank, counts.getOrDefault(rank, 0) + 1);
        }
        return counts;
    }

    private static long calculateTotalPrize(Map<MatchRank, Integer> rankCounts) {
        long sum = 0;
        for (Map.Entry<MatchRank, Integer> entry : rankCounts.entrySet()) {
            sum += (long) entry.getKey().getMoney() * entry.getValue();
        }
        return sum;
    }

    public Map<MatchRank, Integer> getRankCounts() {
        return rankCounts;
    }

    public long getTotalPrize() {
        return totalPrize;
    }
}