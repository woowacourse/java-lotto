package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    MISS(0, 0, false),
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false);

    private static final int MIN = 3;

    private final int count;
    private final int prize;
    private final boolean bonus;

    Rank(int count, int prize, boolean bonus) {
        this.count = count;
        this.prize = prize;
        this.bonus = bonus;
    }

    public static Rank valueOf(int count, boolean bonusResult) {
        if (count < MIN) {
            return MISS;
        }

        if (SECOND.matchCount(count) && SECOND.matchBonus(bonusResult)) {
            return SECOND;
        }

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount(count) && rank != SECOND)
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

    private boolean matchCount(int countOfMatch) {
        return this.count == countOfMatch;
    }

    private boolean matchBonus(boolean bonus) {
        return this.bonus == bonus;
    }
}
