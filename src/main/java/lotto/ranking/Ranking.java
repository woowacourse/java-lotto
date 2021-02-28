package lotto.ranking;

import java.util.Arrays;

public enum Ranking {
    FIFTH(3, 5_000),
    FORTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000),
    NOTHING(0, 0);

    private final int matchCount;
    private final int price;
    private final boolean matchBonus;

    Ranking(int matchCount, int price) {
        this(matchCount, price, false);
    }

    Ranking(int matchCount, int price, boolean matchBonus) {
        this.matchCount = matchCount;
        this.price = price;
        this.matchBonus = matchBonus;
    }

    public static Ranking makePrice(int match, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(ranking -> ranking.matchCount == match)
                .filter(ranking -> ranking.matchBonus == bonusMatch)
                .findFirst()
                .orElse(NOTHING);
    }

    public int calculatePrize(int rankingCount) {
        return this.price * rankingCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }
}
