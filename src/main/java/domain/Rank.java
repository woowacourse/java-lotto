package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST(6, 2_000_000_000L, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 6),
    SECOND(5, 30_000_000L, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 5 && hasBonusNumber),
    THIRD(5, 1_500_000L, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 5 && !hasBonusNumber),
    FOURTH(4, 50_000L, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 4),
    FIFTH(3, 5_000L, (sameNumberCount, hasBonusNumber) -> sameNumberCount == 3),
    NO_MATCH(0, 0L, (sameNumberCount, hasBonusNumber) -> sameNumberCount < 3);

    private final long prize;
    private final BiPredicate<Long, Boolean> rankPredicate;

    Rank(int sameNumberCount, long prize, BiPredicate<Long, Boolean> rankPredicate) {
        this.prize = prize;
        this.rankPredicate = rankPredicate;
    }

    public static Rank of(long sameNumberCount, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.rankPredicate.test(sameNumberCount, hasBonusNumber))
                .findAny()
                .orElse(NO_MATCH);
    }

    public long getPrize() {
        return prize;
    }
}
