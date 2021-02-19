package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(6, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NOTHING(0, 0L);

    private final int matchCount;
    private final long reward;


    Rank(int matchCount, long reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public long getReward() {
        return reward;
    }

    public static LottoStatisticResult match(Lottos lottos, WinningLotto winningLotto) {
        List<Lotto> lottoGroup = lottos.getLottos();

        Map<Rank, Long> rankCount = lottoGroup.stream()
                                              .collect(Collectors.groupingBy(winningLotto::match,
                                                  Collectors.counting()));

        return new LottoStatisticResult(rankCount);
    }

    public static Rank getRankByMatchCount(long matchCount, boolean requiredBonus) {
        return Arrays.stream(Rank.values())
                     .filter(rank -> isSameMatchCount(rank, matchCount))
                     .map(rank -> getMatchRank(rank, requiredBonus))
                     .findFirst()
                     .orElse(Rank.NOTHING);
    }

    private static Rank getMatchRank(Rank rank, boolean requiredBonus) {
        if (isSameMatchCount(Rank.SECOND, rank.matchCount) && requiredBonus) {
            return SECOND;
        }

        if (isSameMatchCount(Rank.THIRD, rank.matchCount)) {
            return THIRD;
        }

        return rank;
    }

    private static boolean isSameMatchCount(Rank rank, long matchCount) {
        return rank.matchCount == matchCount;
    }
    
    public int getMatchCount() {
        return matchCount;
    }
    
    
    public static int compareMatchCount(Rank preRank, Rank postRank) {
        return preRank.matchCount - postRank.matchCount;
    }
    
    public static int compareReward(Rank preRank, Rank postRank) {
        return (int) (preRank.reward - postRank.reward);
    }
}
