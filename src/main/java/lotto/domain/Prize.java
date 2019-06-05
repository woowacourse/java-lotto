package lotto.domain;

import lotto.domain.exception.NotMatchLottoPrizeException;

public enum Prize {
    NONE(0, 0, false),
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false);

    public static final int MIN_PRIZE_NUMBER = 3;
    private final int countOfNumber;
    private final int winningAmount;
    private final boolean requiredBonus;

    Prize(final int countOfNumber, final int winningAmount, final boolean requiredBonus) {
        this.countOfNumber = countOfNumber;
        this.winningAmount = winningAmount;
        this.requiredBonus = requiredBonus;
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

        if (SECOND.requiredBonus == hasBonus && SECOND.matchCount(countOfNumber)) {
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
