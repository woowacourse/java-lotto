package domain.result;

import java.util.Arrays;

public enum LottoRank {
    NONE_MATCHES(0, 0, false),
    THREE_MATCHES(3, 5_000, false),
    FOUR_MATCHES(4, 50_000, false),
    FIVE_MATCHES(5, 1_500_000, false),
    FIVE_AND_BONUS_MATCHES(5, 30_000_000, true),
    SIX_MATCHES(6, 2_000_000_000, false);

    private final int matches;
    private final int price;
    private final boolean isBonus;

    LottoRank(int matches, int price, boolean isBonus) {
        this.matches = matches;
        this.price = price;
        this.isBonus = isBonus;
    }

    public int getMatches() {
        return matches;
    }

    public static LottoRank findByMatches(int matches) {
        return Arrays.stream(values()).filter(lottoRank -> lottoRank.isSameMatches(matches))
                .findFirst().orElse(NONE_MATCHES);
    }

    private boolean isSameMatches(int matches) {
        return this.matches == matches;
    }
}
