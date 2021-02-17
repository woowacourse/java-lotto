package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(5, 50_000),
    FOURTH(4, 5_000),
    FIFTH(3, 0),
    NO_MATCH(0, 0);

    private int matchCount;
    private int money;

    Rank(int matchCount, int money){
        this.matchCount = matchCount;
        this.money = money;
    }

    public static Rank check(int matchCount, boolean hasBonusNumber){
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.isSameMatchCount(matchCount))
            .filter(rank -> !rank.equals(SECOND) || hasBonusNumber)
            .findFirst()
            .orElse(NO_MATCH);
    }

    private boolean isSameMatchCount(int matchCount){
        return this.matchCount == matchCount;
    }

    public int getMoney(){
        return money;
    }

}
