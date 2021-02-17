package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6,  2_000_000_000),
    SECOND(6,  30_000_000),
    THIRD(5,  1_500_000),
    FOURTH(4,  50_000),
    FIFTH(3,  5_000),
    NOTHING(0,  0);

    private final int matchCount;
    private final int reward;

    Rank(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Map<Rank, Long> match(Lottos lottos, WinningLotto winningLotto) {
        List<Lotto> lottoGroup = lottos.getLottos();

        return lottoGroup.stream()
                         .collect(Collectors.groupingBy(winningLotto::match, Collectors.counting()));
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
}
