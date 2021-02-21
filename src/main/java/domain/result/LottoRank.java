package domain.result;

import java.util.Arrays;

public enum LottoRank {
    NONE_MATCHES(0, 0),
    THREE_MATCHES(3, 5_000),
    FOUR_MATCHES(4, 50_000),
    FIVE_MATCHES(5, 1_500_000),
    FIVE_AND_BONUS_MATCHES(5, 30_000_00),
    SIX_MATCHES(6, 2_000_000_000);

    private final int matches;
    private final int prize;

    LottoRank(final int matches, final int prize) {
        this.matches = matches;
        this.prize = prize;
    }

    public static LottoRank findRankByBonusAndMatches(final boolean isBonus, final int matches) {
        if (!isBonus || matches != 5) {
            return Arrays.stream(values()).filter(lottoRank -> lottoRank.isSameMatches(matches))
                    .findFirst().orElse(NONE_MATCHES);
        }
        return FIVE_AND_BONUS_MATCHES;
    }

    private boolean isSameMatches(final int matches) {
        return this.matches == matches;
    }

    public int getPrize() {
        return prize;
    }
}
