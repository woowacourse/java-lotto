package lotto.domain;

import lotto.domain.exception.NotMatchLottoPrizeException;

public enum Prize {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NONE(0, 0);

    public static final int MIN_PRIZE_NUMBER = 3;
    public static final int MAX_PRIZE_NUMBER = 6;
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

    public static Prize valueOf(int countOfNumber) {
        if (countOfNumber < MIN_PRIZE_NUMBER) {
            return Prize.NONE;
        }
        for (Prize prize : Prize.values()) {
            if (prize.countOfNumber == countOfNumber) {
                return prize;
            }
        }
        throw new NotMatchLottoPrizeException("당첨 갯수가 맞지 않습니다.");
    }
}
