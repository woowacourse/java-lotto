package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Ranking {
    FIRST(2000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    NONE(0, 0, false);

    private final int prize;
    private final int count;
    private final boolean hasBonusNumber;

    Ranking(int prize, int count, boolean hasBonusNumber) {
        this.prize = prize;
        this.count = count;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static Ranking findRanking(int count, boolean hasBonusNumber) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> checkCountAndBonusNumber(ranking, count, hasBonusNumber))
                .findAny()
                .orElse(NONE);
    }

    private static boolean checkCountAndBonusNumber(Ranking ranking, int count, boolean hasBonusNumber) {
        if(count == 5) {
            return ranking.hasBonusNumber == hasBonusNumber;
        }
        return ranking.count == count;
    }

    public long multiple(Integer count) {
        return (long) prize * count;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public boolean getHasBonusBall() {
        return hasBonusNumber;
    }
}
