package domain.result;

import java.util.Arrays;

public enum LottoRank {
    NONE_MATCHES(0, 0, false),
    THREE_MATCHES(3, 5_000, false),
    FOUR_MATCHES(4, 50_000, false),
    FIVE_MATCHES(5, 1_500_000, false),
    FIVE_AND_BONUS_MATCHES(5, 30_000_000, true),
    SIX_MATCHES(6, 2_000_000_000, false);

    private static final String STRING_FORMATTER = "%s개 일치 (%d원)";

    private final int matches;
    private final int prize;
    private final boolean isBonus;

    LottoRank(final int matches, final int prize, final boolean isBonus) {
        this.matches = matches;
        this.prize = prize;
        this.isBonus = isBonus;
    }

    public static LottoRank findByBonusWithMatches(final boolean containBonus, final int matches) {
        if (!containBonus || matches != 5) {
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
