package domain;

public enum LottoRank {
    NONE_MATCHES(0, 0, false),
    THREE_MATCHES(5_000, 3, false),
    FOUR_MATCHES(50_000, 4, false),
    FIVE_MATCHES(1_500_000, 5, false),
    FIVE_AND_BONUS_MATCHES(30_000_000, 5, true),
    SIX_MATCHES(2_000_000_000, 6, false);

    private final int prizeMoney;
    private final int lottoBallMatch;
    private final boolean bonusMatch;

    LottoRank(final int prizeMoney, final int lottoBallMatch, final boolean bonusMatch) {
        this.prizeMoney = prizeMoney;
        this.lottoBallMatch = lottoBallMatch;
        this.bonusMatch = bonusMatch;
    }

    public static LottoRank findLottoRank(final int lottoBallMatch, final boolean bonusMatch) {
        if (lottoBallMatch == 3) {
            return THREE_MATCHES;
        }
        if (lottoBallMatch == 4) {
            return FOUR_MATCHES;
        }
        if (lottoBallMatch == 5 && !bonusMatch) {
            return FIVE_MATCHES;
        }
        if (lottoBallMatch == 5 && bonusMatch) {
            return FIVE_AND_BONUS_MATCHES;
        }
        if (lottoBallMatch == 6) {
            return SIX_MATCHES;
        }
        return NONE_MATCHES;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }
}
