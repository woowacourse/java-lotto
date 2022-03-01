package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Ranking {
    FIRST(2000000000, 6),
    SECOND(30000000, 5),
    THIRD(1500000, 5),
    FOURTH(50000, 4),
    FIFTH(5000, 3);

    private final int prize;
    private final int hitCount;

    Ranking(int prize, int count) {
        this.prize = prize;
        this.hitCount = count;
    }

    public static Optional<Ranking> findRanking(int hitCount, boolean hasBonusNumber) {
        if (hitCount == SECOND.hitCount && hasBonusNumber) {
            return Optional.of(SECOND);
        }
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking != SECOND)
                .filter(ranking -> ranking.hitCount == hitCount)
                .findAny();
    }

    public long multiplyPrizeWithCount(Integer count) {
        return (long) prize * count;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isSecond() {
        return this == SECOND;
    }
}
