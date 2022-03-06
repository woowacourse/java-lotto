package lotto.domain.matchkind;

import java.util.Arrays;

public enum WinningKind {
    LOWER_THAN_THREE(0, false, 0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000);

    private final int matchCount;
    private final boolean bonusNumberHit;
    private final long winningAmount;

    WinningKind(final int matchCount, final boolean bonus, final long winningAmount) {
        this.matchCount = matchCount;
        this.bonusNumberHit = bonus;
        this.winningAmount = winningAmount;
    }

    public static WinningKind from(final int matchCount, final boolean bonusNumberHit) {
        if (isFiveBonus(matchCount, bonusNumberHit)) {
            return FIVE_BONUS;
        }
        return Arrays.stream(values())
                .filter(matchKind -> matchKind.matchCount == matchCount)
                .filter(matchKind -> !matchKind.bonusNumberHit)
                .findAny()
                .orElse(LOWER_THAN_THREE);
    }

    private static boolean isFiveBonus(int matchCount, boolean bonusNumberHit) {
        return bonusNumberHit && matchCount == 5;
    }

    public long getProfit(final int winningCount) {
        return winningAmount * winningCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean hasSameNumberWithBonus() {
        return bonusNumberHit;
    }

    public int getWinningAmount() {
        return (int) winningAmount;
    }
}
