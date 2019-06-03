package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final String RESULT_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final String ERROR_VALUE = "유효하지 않은 값입니다.";
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

        for (Rank rank : Rank.values()) {
            if (rank.count == count && rank != SECOND) {
                return rank;
            }
        }

        throw new IllegalArgumentException(ERROR_VALUE);
    }

    public String getMatchString(List<Rank> ranks) {
        int matchCount = 0;

        for (Rank rank : ranks) {
            matchCount = plusCount(matchCount, rank);
        }

        if (SECOND.equals(this)) {
            return String.format(RESULT_BONUS_FORMAT, this.count, this.prize, matchCount);
        }

        return String.format(RESULT_FORMAT, this.count, this.prize, matchCount);
    }

    public int getPrize(List<Rank> ranks) {
        int sum = 0;

        for (Rank rank : ranks) {
            sum = getSum(sum, rank);
        }

        return sum;
    }

    private int getSum(int sum, Rank rank) {
        if (this.equals(rank)) {
            sum += rank.prize;
        }
        return sum;
    }

    private int plusCount(int matchCount, Rank rank) {
        if (this.equals(rank)) {
            matchCount++;
        }
        return matchCount;
    }
}
