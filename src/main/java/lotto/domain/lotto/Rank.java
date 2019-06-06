package lotto.domain.lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final int ZERO = 0;

    private final int countOfMatch;
    private final long winningMoney;

    Rank(int countOfMatch, long winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < FIFTH.countOfMatch && countOfMatch >= ZERO) {
            return MISS;
        }

        if (SECOND.match(countOfMatch) && matchBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.match(countOfMatch))
                .filter(Rank::isNotSecond)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    private boolean match(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public boolean isMiss() {
        return this == MISS;
    }

    private boolean isNotSecond() {
        return this != SECOND;
    }

    public long calculateWinningMoney(long countOfRank) {
        return this.winningMoney * countOfRank;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public long getWinningMoney() {
        return winningMoney;
    }
}
