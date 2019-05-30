package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, 2_000_000_000), // 1등
    SECOND(5, 30_000_000), // 2등
    THIRD(5, 1_500_000), // 3등
    FOURTH(4, 50_000), // 4등
    FIFTH(3, 5_000), // 5등
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningMoney;

    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    int getWinningMoney() {
        return winningMoney;
    }

    public StringBuilder getMessage() {
        StringBuilder message = new StringBuilder();
        message.append(countOfMatch);
        message.append("개 일치");
        if (this == SECOND) {
            message.append(", 보너스 볼 일치");
        }
        message.append("(");
        message.append(winningMoney);
        message.append("원)- ");
        return message;
    }

    static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && matchBonus) {
            return SECOND;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch) && rank != SECOND) {
                return rank;
            }
        }

        throw new IllegalArgumentException(countOfMatch + "는 유효하지 않은 값입니다.");
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    static List<Rank> winningValues() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .collect(Collectors.toList());
    }
}
