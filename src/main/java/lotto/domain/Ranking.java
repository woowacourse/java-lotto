package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Ranking {
    FIRST_PLACE(2000000000, 6),
    SECOND_PLACE(30000000, 5),
    THIRD_PLACE(1500000, 5),
    FOURTH_PLACE(50000, 4),
    FIFTH_PLACE(5000, 3);

    private final int prize;
    private final int hitCount;

    Ranking(int prize, int count) {
        this.prize = prize;
        this.hitCount = count;
    }

    public static Optional<Ranking> findRanking(int hitCount, boolean hasBonusNumber) {
        if (hitCount == SECOND_PLACE.hitCount && hasBonusNumber) {
            return Optional.of(SECOND_PLACE);
        }
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking != SECOND_PLACE)
                .filter(ranking -> ranking.hitCount == hitCount)
                .findAny();
    }

    public long multiplyPrizeWithCount(int count) {
        return (long) prize * count;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isSecond() {
        return this == SECOND_PLACE;
    }
}
