package lotto.domain;

import lotto.domain.exception.NotMatchLottoPrizeException;

public enum Prize {
    NONE(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    public static final int MIN_PRIZE_NUMBER = 3;
    private final int countOfNumber;
    private final int winningAmount;

    Prize(final int countOfNumber, final int winningAmount) {
        this.countOfNumber = countOfNumber;
        this.winningAmount = winningAmount;
    }

    public int getCountOfNumber() {
        return countOfNumber;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public static Prize valueOf(int countOfNumber, boolean hasBonus) {
        if (countOfNumber < MIN_PRIZE_NUMBER) {
            return NONE;
        }

        if (SECOND.matchCount(countOfNumber) && hasBonus) {
            return SECOND;
        }

        for (Prize prize : Prize.values()) {
            if (prize != SECOND && prize.matchCount(countOfNumber)) {
                return prize;
            }
        }
        throw new NotMatchLottoPrizeException("당첨 갯수가 맞지 않습니다.");
    }

    private boolean matchCount(int countOfNumber) {
        return this.countOfNumber == countOfNumber;
    }
}
