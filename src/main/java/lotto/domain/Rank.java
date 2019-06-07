package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final int MIN = 3;
    private static final int COUNT_FIVE = 5;

    private final int count;
    private final int prize;

    Rank(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static Rank valueOf(int count, boolean bonusResult) {
        if (count < MIN) {
            return MISS;
        }

        if (count == COUNT_FIVE && bonusResult) {
            return SECOND;
        }

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.count == count && rank != SECOND)
                .findAny().orElseThrow(IllegalArgumentException::new);
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchingCount(List<Rank> ranks) {
        int matchCount = 0;

        for (Rank rank : ranks) {
            matchCount = plusCount(matchCount, rank);
        }

        return matchCount;
    }

    public static List<Integer> providePrizeResult(List<Rank> ranks) {
        List<Integer> prizes = new ArrayList<>();

        for (Rank rank : ranks) {
            prizes.add(rank.prize);
        }

        return prizes;
    }

    private int plusCount(int matchCount, Rank ranks) {
        if (this.equals(ranks)) {
            matchCount++;
        }
        return matchCount;
    }
}
