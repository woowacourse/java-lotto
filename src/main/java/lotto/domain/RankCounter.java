package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class RankCounter {

    private final Map<Rank, Integer> rankCount;

    public RankCounter(Lottos lottos, WinningNumbers winningNumbers) {
        this.rankCount = new EnumMap<>(Rank.class);
        initRankCount();
        calculateRank(lottos, winningNumbers);
    }

    private void calculateRank(Lottos lottos, WinningNumbers winningNumbers) {
        lottos.getLottos().stream()
                .map(lotto -> lotto.getRank(winningNumbers))
                .forEach(this::increaseCount);
    }

    private void initRankCount() {
        Arrays.stream(Rank.values())
                        .forEach(rank -> rankCount.put(rank, 0));
    }

    private void increaseCount(Rank rank) {
        rankCount.computeIfPresent(rank, (keyRank, count) -> count + 1);
    }

    public int getCountOfRank(Rank rank) {
        return rankCount.get(rank);
    }

    public long getTotalPrize() {
        return rankCount.entrySet().stream()
                .mapToLong(m -> m.getKey().multiplyPrizeBy(m.getValue()))
                .sum();
    }
}
