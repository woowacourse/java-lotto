package lotto.domain;

import java.util.Arrays;

public enum LottoMatchKind {
    LOWER_THAN_THREE(0, false, 0),
    THREE(3, false, 5000),
    FOUR(4, false, 50000),
    FIVE(5, false, 1500000),
    FIVE_BONUS(5, true, 30000000),
    SIX(6, false, 2000000000);

    private final int matchCount;
    private final boolean bonus;
    private final long winningAmount;

    LottoMatchKind(final int matchCount, final boolean bonus, final long winningAmount) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.winningAmount = winningAmount;
    }

    public static LottoMatchKind from(final int matchCount, final boolean bonus) {
        return Arrays.stream(values())
                .filter(matchKind -> matchKind.matchCount == matchCount)
                .filter(matchKind -> matchKind.bonus == bonus)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("매치 개수는 3개 미만이거나 6개 초과입니다."));
    }

    public long getProfit(final int countOfMatchedLottoNumbers) {
        return winningAmount * countOfMatchedLottoNumbers;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean hasSameNumberWithBonus() {
        return bonus;
    }

    public int getWinningAmount() {
        return (int) winningAmount;
    }
}
