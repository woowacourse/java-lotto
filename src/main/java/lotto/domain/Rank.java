package lotto.domain;

import java.util.stream.Stream;

public enum Rank {
    FIRST(6,2000000000),
    SECOND(5,30000000),
    THIRD(5,1500000),
    FORTH(4,50000),
    FIFTH(3,5000),
    NONE(0,0);

    private final int matchCount;
    private final int money;

    Rank(final int matchCount, final int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    public static Rank valueOf(int matchCount, boolean isMatchBonusBall){
        if(matchCount == 5 && isMatchBonusBall){
            return SECOND;
        }

        if(matchCount >= FIFTH.matchCount){
            return Stream.of(Rank.values())
                    .filter(r -> r.matchCount== matchCount)
                    .findFirst()
                    .get();
        }

        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMoney() {
        return money;
    }
}
