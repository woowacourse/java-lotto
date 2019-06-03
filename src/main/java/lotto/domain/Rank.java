package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    FIRST(6, 2_000_000_000);
    private final int numOfMatching;
    private final long prize;

    Rank(int numOfMatching, long prize) {
        this.numOfMatching = numOfMatching;
        this.prize = prize;
    }

    public static Rank valueOf(int numOfMatching) {
        for (Rank value : values()) {
            if (value.numOfMatching == numOfMatching) {
                return value;
            }
        }
        return MISS;
    }

    public static List<Rank> valuesWithoutMISS() {
        List<Rank> ranks = new ArrayList<>(Arrays.asList(Rank.values()));
        ranks.remove(Rank.MISS);
        return ranks;
    }

    public int getNumOfMatching() {
        return numOfMatching;
    }

    public Long getPrize() {
        return prize;
    }
}
