package lotto.domain.matchkind;

import java.util.Arrays;

public enum LottoMatchKind {
    LOWER_THAN_THREE(0, false, 0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000);

    private static final String MATCH_KIND_NOT_FOUND_EXCEPTION_MESSAGE = "존재하지 않는 당첨 종류가 발생했습니다.";

    private final int matchCount;
    private final boolean bonusNumberHit;
    private final long winningAmount;

    LottoMatchKind(final int matchCount, final boolean bonus, final long winningAmount) {
        this.matchCount = matchCount;
        this.bonusNumberHit = bonus;
        this.winningAmount = winningAmount;
    }

    public static LottoMatchKind from(final int matchCount, final boolean bonus) {
        return Arrays.stream(values())
                .filter(matchKind -> matchKind.matchCount == matchCount)
                .filter(matchKind -> matchKind.bonusNumberHit == bonus)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MATCH_KIND_NOT_FOUND_EXCEPTION_MESSAGE));
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
