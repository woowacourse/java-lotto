package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RankStatistic {

    private final Map<Rank, Integer> rankCount;

    public RankStatistic(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.rankCount = calculateRankCount(lottos, winningNumbers);
    }

    private EnumMap<Rank, Integer> calculateRankCount(List<Lotto> lottos, WinningNumbers winningNumbers) {
        EnumMap<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        lottos.forEach(lotto -> increaseCount(rankCount, winningNumbers.findRankOf(lotto)));
        return rankCount;
    }

    private void increaseCount(EnumMap<Rank, Integer> rankCount, Rank rank) {
        rankCount.putIfAbsent(rank, 0);
        rankCount.computeIfPresent(rank, (key, value) -> value + 1);
    }

    public long getTotalWinningPrize() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.getPrize() * rankCount.getOrDefault(rank, 0))
                .sum();
    }

    public int getCount(Rank rank) {
        return rankCount.getOrDefault(rank, 0);
    }
}
