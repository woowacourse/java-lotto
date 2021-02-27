package lotto.domain;

import java.util.Arrays;
import lotto.exception.InvalidLottoHitCountException;

public enum Reward {

    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    private final int hitCount;
    private final int winningMoney;
    private final boolean isHitBonus;

    Reward(final int hitCount, final int winningMoney, final boolean isHitBonus) {
        this.hitCount = hitCount;
        this.winningMoney = winningMoney;
        this.isHitBonus = isHitBonus;
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
            .filter(reword -> reword.matchHitCount(hitCount, isHitBonus))
            .findFirst()
            .orElse(Reward.NONE);
    }

    private boolean matchHitCount(final int hitCount, final boolean isHitBonus) {
        if (this.isHitBonus) {
            return hitCount == this.hitCount && isHitBonus;
        }
        return hitCount == this.hitCount;
    }

    private static void validateHitCount(final int value) {
        if (value < NONE.hitCount || value > FIRST.hitCount) {
            throw new InvalidLottoHitCountException();
        }
    }
}
