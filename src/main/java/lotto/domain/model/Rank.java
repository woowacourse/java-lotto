package lotto.domain.model;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FORTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(matchCount) && matchBonus) {
            return SECOND;
        }

        return Arrays.stream(values()).
                filter( rank -> rank.matchCount(matchCount) && rank != Rank.SECOND)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(matchCount + "는 유효하지 않은 값입니다."));
    }

    private boolean matchCount(int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public String toString() {
        StringBuilder rankInfo = new StringBuilder();
        rankInfo.append(this.matchCount);
        rankInfo.append("개 일치,");
        if (this.prize == SECOND.prize) {
            rankInfo.append("보너스 볼 일치");
        }
        rankInfo.append("(");
        rankInfo.append(this.prize);
        rankInfo.append("원)- ");
        return rankInfo.toString();
    }
}
