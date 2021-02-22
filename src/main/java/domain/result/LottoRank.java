package domain.result;

import java.util.Arrays;

public enum LottoRank {
    NONE_MATCHES(0, 0, false),
    THREE_MATCHES(3, 5_000, false),
    FOUR_MATCHES(4, 50_000, false),
    FIVE_MATCHES(5, 1_500_000, false),
    FIVE_AND_BONUS_MATCHES(5, 30_000_00, true),
    SIX_MATCHES(6, 2_000_000_000, false);

    private final int matches;
    private final int prize;
    private final boolean isSecond;

    LottoRank(final int matches, final int prize, boolean isSecond) {
        this.matches = matches;
        this.prize = prize;
        this.isSecond = isSecond;
    }

    public static LottoRank findRankByBonusAndMatches(final boolean isBonus, final int matches) {
        if (!isBonus || matches != 5) {
            return Arrays.stream(values()).filter(lottoRank -> lottoRank.isSameMatches(matches))
                    .findFirst().orElse(NONE_MATCHES);
        }
        return FIVE_AND_BONUS_MATCHES;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatches() {
        return matches;
    }

    public boolean isSecond() {
        return isSecond;
    }

    public boolean hasMatches() {
        return this != NONE_MATCHES;
    }

    private boolean isSameMatches(final int matches) {
        return this.matches == matches;
    }
}
