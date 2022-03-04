package lotto.domain.matchkind;

import java.util.Arrays;

public enum LottoMatchKind {
    LOWER_THAN_THREE(0, false, 0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000);

    private final int matchCount;
    private final boolean bonusNumberHit;
    private final long winningAmount;

    LottoMatchKind(final int matchCount, final boolean bonus, final long winningAmount) {
        this.matchCount = matchCount;
        this.bonusNumberHit = bonus;
        this.winningAmount = winningAmount;
    }

    public static LottoMatchKind from(final int matchCount, final boolean bonusNumberHit) {
        if (matchCount == 5 && bonusNumberHit) {
            return FIVE_BONUS;
        }
        return Arrays.stream(values())
                .filter(matchKind -> matchKind.matchCount == matchCount)
                .findFirst()
                .orElse(LOWER_THAN_THREE);
    }

    public long getProfit(final int countOfMatchedLottoNumbers) {
        return winningAmount * countOfMatchedLottoNumbers;
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
