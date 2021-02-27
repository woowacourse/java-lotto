package domain.result;

import java.util.Arrays;

public enum LottoRank {
    NONE_PLACE(0, 0, false),
    FIFTH_PLACE(3, 5_000, false),
    FOURTH_MATCHES(4, 50_000, false),
    THIRD_PLACE(5, 1_500_000, false),
    SECOND_PLACE(5, 30_000_00, true),
    FIRST_PLACE(6, 2_000_000_000, false);

    private static final int FIVE_MATCHES = 5;

    private final int matches;
    private final int prize;
    private final boolean isSecond;

    LottoRank(final int matches, final int prize, boolean isSecond) {
        this.matches = matches;
        this.prize = prize;
        this.isSecond = isSecond;
    }

    public static LottoRank findRankByBonusAndMatches(final boolean hasBonus, final int matches) {
        if (!hasBonus || matches != FIVE_MATCHES) {
            return Arrays.stream(values()).filter(lottoRank -> lottoRank.isSameMatches(matches))
                    .findFirst().orElse(NONE_PLACE);
        }
        return SECOND_PLACE;
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
        return this != NONE_PLACE;
    }

    private boolean isSameMatches(final int matches) {
        return this.matches == matches;
    }
}
