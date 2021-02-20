package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lotto.domain.LottoStatistics.DEFAULT_STATISTICS_FORMAT;
import static lotto.domain.LottoStatistics.STATISTICS_FORMAT_FOR_SECOND;

public enum Rank {
    FIRST(6, 2_000_000_000, DEFAULT_STATISTICS_FORMAT),
    SECOND(5, 30_000_000, STATISTICS_FORMAT_FOR_SECOND),
    THIRD(5, 1_500_000, DEFAULT_STATISTICS_FORMAT),
    FOURTH(4, 50_000, DEFAULT_STATISTICS_FORMAT),
    FIFTH(3, 5_000, DEFAULT_STATISTICS_FORMAT),
    NONE(0, 0, DEFAULT_STATISTICS_FORMAT);

    public static final int RANK_START_INDEX = 0;
    public static final int RANK_END_INDEX = 5;

    private final int count;
    private final int prize;
    private final String message;

    Rank(int count, int prize, String message) {
        this.count = count;
        this.prize = prize;
        this.message = String.format(message, this.count, this.prize);
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

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
