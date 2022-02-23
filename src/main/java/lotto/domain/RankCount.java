package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RankCount {

    private final Map<Rank, Integer> rankCount;

    public RankCount(Lottos lottos, WinningNumbers winningNumbers) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> rankCount.put(rank, 0));
        this.rankCount = rankCount;
        calculateRank(lottos, winningNumbers);
    }

    private void calculateRank(Lottos lottos, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottos.getLottos()) {
            increaseCount(lotto.getRank(winningNumbers));
        }
    }

    private void increaseCount(Rank rank) {
        rankCount.put(rank, rankCount.get(rank) + 1);
    }

    public int getCount(Rank rank) {
        return rankCount.get(rank);
    }
}
