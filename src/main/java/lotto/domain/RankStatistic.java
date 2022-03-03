package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class RankStatistic {

    private final Map<Rank, Integer> rankCount;

    public RankStatistic(User user, WinningNumbers winningNumbers) {
        this.rankCount = calculateRankCount(user, winningNumbers);
    }

    private EnumMap<Rank, Integer> calculateRankCount(User user, WinningNumbers winningNumbers) {
        EnumMap<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        user.getLottos().forEach(lotto -> increaseCount(rankCount, winningNumbers.findRankOf(lotto)));
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
