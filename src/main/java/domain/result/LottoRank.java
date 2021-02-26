package domain.result;

import java.util.Arrays;

public enum LottoRank {
    NO_PRIZE(0, 0, false),
    FIFTH_PRIZE(5_000, 3, false),
    FOURTH_PRIZE(50_000, 4, false),
    THIRD_PRIZE(1_500_000, 5, false),
    SECOND_PRIZE(30_000_000, 5, true),
    FIRST_PRIZE(2_000_000_000, 6, false);

    private static final int DETECT_BONUS = 5;

    private final int prizeMoney;
    private final int lottoBallMatch;
    private final boolean bonusMatch;

    LottoRank(final int prizeMoney, final int lottoBallMatch, final boolean bonusMatch) {
        this.prizeMoney = prizeMoney;
        this.lottoBallMatch = lottoBallMatch;
        this.bonusMatch = bonusMatch;
    }

    public static LottoRank findLottoRank(final int lottoBallMatch, final boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank.checkLottoBallMatch(lottoBallMatch))
                .filter(lottoRank -> lottoRank.checkBonusMatch(lottoBallMatch, bonusMatch))
                .findFirst()
                .orElse(NO_PRIZE);
    }

    private boolean checkLottoBallMatch(final int lottoBallMatch) {
        return (this.lottoBallMatch == lottoBallMatch);
    }

    private boolean checkBonusMatch(final int lottoBallMatch, final boolean bonusMatch) {
        if (lottoBallMatch != DETECT_BONUS) {
            return true;
        }
        return (this.bonusMatch == bonusMatch);
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }
}
