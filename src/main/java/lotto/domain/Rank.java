package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    public static final int RANK_START_INDEX = 0;
    public static final int RANK_END_INDEX = 5;
    public static final int INDEX_OF_SECOND_RANK = 3;

    private final int count;
    private final int prize;

    Rank(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public static Rank getCorrespondingRank(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(result -> result.count == matchCount)
                .filter(result -> !SECOND.equals(result) || bonusMatch)
                .findFirst()
                .orElse(NONE);
    }

    public static List<Rank> getRanksForStatistics() {
        List<Rank> values = Arrays.asList(values()).subList(RANK_START_INDEX, RANK_END_INDEX);
        Collections.reverse(values);
        return values;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }
}
