package lottogame.domain;

import java.util.Arrays;

public enum Rank {
    FAIL(0, false, 0),
    RANK5(3, false, 5_000),
    RANK4(4, false, 50_000),
    RANK3(5, false, 1_500_000),
    RANK2(5, true, 30_000_000),
    RANK1(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean hasBonus;
    private final int price;

    Rank(final int matchCount, final boolean hasBonus, final int price) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.price = price;
    }

    public static Rank of(final int matchCount, final boolean hasBonus) {
        return Arrays.stream(values())
            .filter(rank -> rank.isSameMatchCount(matchCount))
            .filter(rank -> rank.isSameBonus(hasBonus))
            .findAny()
            .orElse(FAIL);
    }

    private boolean isSameMatchCount(final int matchCount) {
        return this.matchCount == matchCount;
    }

    private boolean isSameBonus(boolean hasBonus) {
        return this.hasBonus == hasBonus;
    }

    public int getPrice() {
        return this.price;
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public boolean hasBonus() {
        return this.hasBonus;
    }
}
