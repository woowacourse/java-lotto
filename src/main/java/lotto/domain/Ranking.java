package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Ranking {
    FIRST_PLACE(2000000000, 6),
    SECOND_PLACE(30000000, 5),
    THIRD_PLACE(1500000, 5),
    FOURTH_PLACE(50000, 4),
    FIFTH_PLACE(5000, 3),
    NONE_PLACE(0, 0);

    private final int prize;
    private final int hitCount;

    Ranking(int prize, int count) {
        this.prize = prize;
        this.hitCount = count;
    }

    public static Ranking of(int hitCount, boolean hasBonusNumber) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking.hitCount == hitCount)
                .filter(ranking -> ranking != SECOND_PLACE || hasBonusNumber)
                .findFirst()
                .orElse(NONE_PLACE);
    }

    public long multiplyPrizeWithCount(int count) {
        return (long) prize * count;
    }

    public boolean isSecond() {
        return this == SECOND_PLACE;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getPrize() {
        return prize;
    }

    public static List<Ranking> valuesWithoutDefault() {
        return Arrays.stream(Ranking.values()).filter(ranking -> ranking != Ranking.NONE_PLACE)
                .collect(Collectors.toList());
    }
}
