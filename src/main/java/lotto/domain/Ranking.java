package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Ranking {
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, false),
    FIFTH(5000, 3, false);

    private final int prize;
    private final int count;
    private final boolean hasBonusNumber;

    Ranking(int prize, int count, boolean hasBonusNumber) {
        this.prize = prize;
        this.count = count;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static Ranking findRanking(int cnt, boolean hasBonusNumber) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking.count == cnt && ranking.hasBonusNumber == hasBonusNumber)
                .findAny()
                .orElse(null);
    }

    public static Ranking[] sortByPrize() {
        Ranking[] arr = Ranking.values();
        Arrays.sort(arr, Comparator.comparingInt(a -> a.prize));
        return arr;
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
