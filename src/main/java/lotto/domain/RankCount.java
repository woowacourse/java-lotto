package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RankCount {

    private final Map<Rank, Integer> rankCount;

    public RankCount(Lottos lottos, WinningLotto winningLotto, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> rankCount.put(rank, 0));
        this.rankCount = rankCount;
        calculateRankCount(lottos, winningLotto, bonusNumber);
    }

    private void calculateRankCount(Lottos lottos, WinningLotto winningLotto, BonusNumber bonusNumber) {
        for (Lotto lotto : lottos.getLottos()) {
            increaseCount(lotto.getRank(winningLotto, bonusNumber));
        }
    }

    private void increaseCount(Rank rank) {
        rankCount.put(rank, rankCount.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return rankCount.get(rank);
    }

    public long getTotalPrize() {
        return Arrays.stream(Rank.values())
                .mapToLong(rank -> rank.multiplyPrizeBy(rankCount.get(rank)))
                .sum();
    }
}
