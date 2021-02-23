package domain;

import java.util.Arrays;

public enum LottoRank {
    NONE_MATCHES(0, 0, false),
    THREE_MATCHES(5_000, 3, false),
    FOUR_MATCHES(50_000, 4, false),
    FIVE_MATCHES(1_500_000, 5, false),
    FIVE_AND_BONUS_MATCHES(30_000_000, 5, true),
    SIX_MATCHES(2_000_000_000, 6, false);

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
                .orElse(NONE_MATCHES);
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
