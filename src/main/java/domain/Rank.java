package domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),

    NONE(0, 0, false);

    private int count;
    private int price;
    private boolean bonusFlag;

    Rank(int count, int price, boolean bonusFlag){
        this.count = count;
        this.price = price;
        this.bonusFlag = bonusFlag;
    }

    public static Rank matchRank(int matchCount, boolean bonusFlag) {
        for (Rank rank : Rank.values()) {
            if(rank.count == matchCount && rank.bonusFlag == bonusFlag) {
                return rank;
            }
        }
        return NONE;
    }
}
