package lotto.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    FAIL(0, 0);

    private final int correctedBall;
    private final long money;

    Rank(int correctedBall, long money) {
        this.correctedBall = correctedBall;
        this.money = money;
    }

    public static Rank find(int matchWinningNumbers, boolean matchBonus) {
        if (matchBonus && matchWinningNumbers == SECOND.correctedBall) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.correctedBall == matchWinningNumbers && rank != Rank.SECOND)
                .findFirst()
                .orElse(FAIL);
    }

    public static List<Rank> getRanksToPrint() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.FAIL)
                .collect(Collectors.toList());
    }

    public int getCorrectedBall() {
        return correctedBall;
    }

    public long getMoney() {
        return money;
    }
}
