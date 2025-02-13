package model;


import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoRank {

    FAIL(0,
            (lottoMatchCount, bonusNumberMatch) -> {
                if (lottoMatchCount < 3) {
                    return true;
                }
                return false;
            }),
    FIFTH_PLACE(5_000,
            (lottoMatchCount, bonusNumberMatch) -> {
                if (lottoMatchCount == 3) {
                    return true;
                }
                return false;
            }),
    FOURTH_PLACE(50_000,
            (lottoMatchCount, bonusNumberMatch) -> {
                if (lottoMatchCount == 4) {
                    return true;
                }
                return false;
            }),
    THIRD_PLACE(1_500_000,
            (lottoMatchCount, bonusNumberMatch) -> {
                if (lottoMatchCount == 5 && !bonusNumberMatch) {
                    return true;
                }
                return false;
            }),
    SECOND_PLACE(30_000_000,
            (lottoMatchCount, bonusNumberMatch) -> {
                if (lottoMatchCount == 5 && bonusNumberMatch) {
                    return true;
                }
                return false;
            }),
    FIRST_PLACE(2_000_000_000,
            (lottoMatchCount, bonusNumberMatch) -> {
                if (lottoMatchCount == 6) {
                    return true;
                }
                return false;
            });
    final int prizeMoney;
    final BiPredicate<Integer, Boolean> incrementIfMatchCondition;

    LottoRank(final int prizeMoney, final BiPredicate<Integer, Boolean> incrementIfMatchCondition) {
        this.prizeMoney = prizeMoney;
        this.incrementIfMatchCondition = incrementIfMatchCondition;
    }

    public static LottoRank of(final Lotto lotto, final WinningNumbers winningNumbers) {
        final int lottoMatchCount = lotto.calculateWinningNumbersMatchCount(winningNumbers);
        final boolean bonusNumberMatch = lotto.isContainsBonusNumber(winningNumbers);

        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank.incrementIfMatchCondition.test(lottoMatchCount, bonusNumberMatch))
                .findAny()
                .orElse(FAIL);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

}
