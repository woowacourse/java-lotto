package lotto.ranking;

import java.util.Arrays;

public enum Ranking {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FORTH(4, 50_000),
    FIFTH(3, 5_000),
    NOTHING(0, 0);

    private final int matchCount;
    private final int price;

    Ranking(int matchCount, int price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public static Ranking makePrice(int match, boolean bonusMatch) {
        if(match == SECOND.matchCount && bonusMatch){
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(n -> n.matchCount == match && n!=SECOND)
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
