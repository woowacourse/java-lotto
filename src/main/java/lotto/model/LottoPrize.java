package lotto.model;

import java.util.Arrays;
import lotto.model.lotto.Lotto;
import lotto.model.winning_lotto.WinningLotto;

public enum LottoPrize {

    SIX(6, false, 2_000_000_000, true),
    FIVE_WITH_BONUS(5, true, 30_000_000, true),
    FIVE(5, false, 1_500_000, true),
    FOUR(4, false, 50_000, true),
    THREE(3, false, 5_000, true),
    TWO(2, false, 0, false),
    ONE(1, false, 0, false),
    ZERO(0, false, 0, false);

    private final int matchCount;
    private final boolean bonusNumberMatches;
    private final int prize;
    private final boolean isWin;

    LottoPrize(int matchCount, boolean bonusNumberMatches, int prize, boolean isWin) {
        this.matchCount = matchCount;
        this.bonusNumberMatches = bonusNumberMatches;
        this.prize = prize;
        this.isWin = isWin;
    }

    public static LottoPrize determine(final Lotto lotto, final WinningLotto winningLotto) {
        int matchCount = winningLotto.getMatchCount(lotto);
        boolean isBonusMatch = winningLotto.isBonusMatch(lotto);

        if (isBonusMatch) {
            return Arrays.stream(values())
                    .filter(value -> value.bonusNumberMatches)
                    .filter(value -> value.matchCount == matchCount)
                    .findFirst()
                    .orElse(fromMatchCount(matchCount));
        }
        return fromMatchCount(matchCount);
    }

    private static LottoPrize fromMatchCount(int matchCount) {
        return Arrays.stream(values())
                .filter(value -> !value.bonusNumberMatches)
                .filter(value -> value.matchCount == matchCount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Prize 를 찾을 수 없습니다."));
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusNumberMatches() {
        return bonusNumberMatches;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isWin() {
        return isWin;
    }
}