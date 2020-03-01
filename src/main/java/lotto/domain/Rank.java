package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Rank {
    FIFTH(5_000,3,false),
    FOURTH(50_000,4,false),
    THIRD(1_500_000,5,false),
    SECOND(30_000_000,5,true),
    FIRST(2_000_000_000,6,false),
    NO_RANK(0,0,false);

    private long winningMoney;
    private int winningCount;
    private boolean hitBonusBall;

    private Rank(long winningMoney, int winningCount, boolean hitBonusBall) {
        this.winningMoney = winningMoney;
        this.winningCount = winningCount;
        this.hitBonusBall = hitBonusBall;
    }

    public static Rank determineRank(long hitCount, boolean hitBonusBall){
        return Arrays.stream(values())
                .filter(rank -> rank.winningCount == hitCount && rank.hitBonusBall == hitBonusBall)
                .findFirst()
                .orElse(Rank.NO_RANK);
    }
}
