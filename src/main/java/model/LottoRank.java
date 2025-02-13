package model;


import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoRank {

    FAIL(0, false, 3,
        (lottoMatchCount, bonusNumberMatch) -> lottoMatchCount < 3),
    FIFTH_PLACE(5_000, false, 3,
        (lottoMatchCount, bonusNumberMatch) -> lottoMatchCount == 3),
    FOURTH_PLACE(50_000, false, 4,
        (lottoMatchCount, bonusNumberMatch) -> lottoMatchCount == 4),
    THIRD_PLACE(1_500_000, false, 5,
        (lottoMatchCount, bonusNumberMatch) -> lottoMatchCount == 5 && !bonusNumberMatch),
    SECOND_PLACE(30_000_000, true, 5,
        (lottoMatchCount, bonusNumberMatch) -> lottoMatchCount == 5 && bonusNumberMatch),
    FIRST_PLACE(2_000_000_000, false, 6,
        (lottoMatchCount, bonusNumberMatch) -> lottoMatchCount == 6);

    final int prizeMoney;
    final boolean isBonusBallMatch;
    final int matchCount;
    final BiPredicate<Integer, Boolean> incrementIfMatchCondition;

    LottoRank(
        final int prizeMoney,
        final boolean isBonusBallMatch,
        final int matchCount,
        final BiPredicate<Integer, Boolean> incrementIfMatchCondition
    ) {
        this.prizeMoney = prizeMoney;
        this.isBonusBallMatch = isBonusBallMatch;
        this.matchCount = matchCount;
        this.incrementIfMatchCondition = incrementIfMatchCondition;
    }

    public static LottoRank of(
        final Lotto lotto,
        final WinningNumbers winningNumbers
    ) {
        final int lottoMatchCount = lotto.calculateWinningNumbersMatchCount(winningNumbers);
        final boolean bonusBallMatch = lotto.isContainsBonusNumber(winningNumbers);

        return Arrays.stream(values())
            .filter(lottoRank -> lottoRank.incrementIfMatchCondition.test(lottoMatchCount, bonusBallMatch))
            .findAny()
            .orElse(FAIL);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusBallMatch() {
        return isBonusBallMatch;
    }
}
