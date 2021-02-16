package lotto.domain;

import lotto.exception.InvalidLottoHitCountException;

public enum Reword {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FORTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private int hitCount;
    private int winningMoney;

    Reword(int hitCount, int winningMoney) {
        this.hitCount = hitCount;
        this.winningMoney = winningMoney;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Reword valueOf(int hitCount, boolean isHitBonus) {
        validateHitCount(hitCount);

        if (hitCount == SECOND.hitCount && isHitBonus) {
            return SECOND;
        }
        if (hitCount == THIRD.hitCount && !isHitBonus) {
            return THIRD;
        }

        for (Reword reword : values()) {
            if (reword.matchHitCount(hitCount)) {
                return reword;
            }
        }

        return NONE;
    }

    private boolean matchHitCount(int hitCount) {
        return hitCount == this.hitCount;
    }

    private static void validateHitCount(int value) {
        if (value < NONE.hitCount || value > FIRST.hitCount) {
            throw new InvalidLottoHitCountException();
        }
    }
}
