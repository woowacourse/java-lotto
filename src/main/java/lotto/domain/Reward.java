package lotto.domain;

import java.util.Arrays;
import lotto.exception.InvalidLottoHitCountException;

public enum Reward {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int hitCount;
    private final int winningMoney;

    Reward(final int hitCount, final int winningMoney) {
        this.hitCount = hitCount;
        this.winningMoney = winningMoney;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Reward valueOf(final int hitCount, final boolean isHitBonus) {
        validateHitCount(hitCount);

        return Arrays.stream(values())
            .filter(reword -> reword.matchHitCount(hitCount))
            .filter(reword -> reword.rewordClassification(isHitBonus))
            .findFirst()
            .orElse(Reward.NONE);
    }

    private boolean rewordClassification(final boolean isHitBonus) {
        return !this.equals(SECOND) || isHitBonus;
    }

    private boolean matchHitCount(final int hitCount) {
        return hitCount == this.hitCount;
    }

    private static void validateHitCount(final int value) {
        if (value < NONE.hitCount || value > FIRST.hitCount) {
            throw new InvalidLottoHitCountException();
        }
    }
}
