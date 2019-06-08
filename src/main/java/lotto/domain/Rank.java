package lotto.domain;

import java.util.stream.Stream;

public enum Rank {
    FIRST(6,2_000_000_000),
    SECOND(5,30_000_000),
    THIRD(5,1_500_000),
    FORTH(4,50_000),
    FIFTH(3,5_000),
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
