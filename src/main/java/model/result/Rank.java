package model.result;

import java.util.Arrays;

public enum Rank {
    MISS(0, false, 0),
    RANK5(3, false, 5000),
    RANK4(4, false, 50000),
    RANK3(5, false, 1500000),
    RANK2(5, true, 30000000),
    RANK1(6, false, 2000000000);

    private final int matchCount;
    private final boolean bonus;
    private final int price;

    Rank(int matchCount, boolean bonus, int price) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean match(int match, boolean bonus) {
        if (this == RANK2 || this == RANK3) {
            return this.matchCount == match && this.bonus == bonus;
        }

        return this.matchCount == match;
    }

    public static Rank judgeRank(int matchRank, boolean bonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.match(matchRank, bonus))
                .findFirst()
                .orElse(MISS);
    }
}