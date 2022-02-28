package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class RankCount {

    private final Map<Rank, Integer> rankCount;

    public RankCount(Lottos lottos, WinningLotto winningLotto, BonusNumber bonusNumber) {
        this.rankCount = new EnumMap<>(calculateRankCount(lottos, winningLotto, bonusNumber));
    }

    private EnumMap<Rank, Integer> calculateRankCount(Lottos lottos,
                                                      WinningLotto winningLotto,
                                                      BonusNumber bonusNumber) {
        EnumMap<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos.getLottos()) {
            increaseCount(rankCount, lotto.getRank(winningLotto, bonusNumber));
        }
        return rankCount;
    }

    private void increaseCount(EnumMap<Rank, Integer> rankCount, Rank rank) {
        rankCount.putIfAbsent(rank, 0);
        rankCount.computeIfPresent(rank, (key, value) -> value + 1);
    }

    public long getTotalPrize() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.calculateTotalPrize(rankCount.getOrDefault(rank, 0)))
                .sum();
    }

    public int getCount(Rank rank) {
        return rankCount.getOrDefault(rank, 0);
    }
}
