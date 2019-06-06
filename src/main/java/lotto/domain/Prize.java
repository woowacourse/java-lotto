package lotto.domain;

import lotto.domain.exception.NotMatchLottoPrizeException;

public enum Prize {
    NONE(0, 0) {
        @Override
        public boolean checkPrizeMatchWith(final int countOfNumber, final boolean hasBonus) {
            return countOfNumber < MIN_PRIZE_NUMBER;
        }
    },
    FIFTH(3, 5_000) {
        @Override
        public boolean checkPrizeMatchWith(final int countOfNumber, final boolean hasBonus) {
            return countOfNumber == FIFTH.countOfNumber;
        }
    },
    FOURTH(4, 50_000) {
        @Override
        public boolean checkPrizeMatchWith(final int countOfNumber, final boolean hasBonus) {
            return countOfNumber == FOURTH.countOfNumber;
        }
    },
    THIRD(5, 1_500_000) {
        @Override
        public boolean checkPrizeMatchWith(final int countOfNumber, final boolean hasBonus) {
            return countOfNumber == THIRD.countOfNumber && !hasBonus;
        }
    },
    SECOND(5, 30_000_000) {
        @Override
        public boolean checkPrizeMatchWith(final int countOfNumber, final boolean hasBonus) {
            return countOfNumber == SECOND.countOfNumber && hasBonus;
        }
    },
    FIRST(6, 2_000_000_000) {
        @Override
        public boolean checkPrizeMatchWith(final int countOfNumber, final boolean hasBonus) {
            return countOfNumber == FIRST.countOfNumber;
        }
    };

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

    public abstract boolean checkPrizeMatchWith(int countOfNumber, boolean hasBonus);

    public static Prize valueOf(int countOfNumber, boolean hasBonus) {
        for (Prize prize : Prize.values()) {
            if (prize.checkPrizeMatchWith(countOfNumber, hasBonus)) {
                return prize;
            }
        }
        throw new NotMatchLottoPrizeException("당첨 갯수가 맞지 않습니다.");
    }
}
