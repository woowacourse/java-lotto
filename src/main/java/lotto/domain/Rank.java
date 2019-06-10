package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);
    private final int numOfMatching;
    private final long prize;

    Rank(int numOfMatching, long prize) {
        this.numOfMatching = numOfMatching;
        this.prize = prize;
    }

    public static Rank valueOf(int numOfMatching, boolean bonus) {
        if (isSecond(numOfMatching, bonus)) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(value -> value.hasSame(numOfMatching))
                .findFirst()
                .orElse(MISS);
    }

    private static boolean isSecond(int numOfMatching, boolean bonus) {
        return SECOND.numOfMatching == numOfMatching && bonus;
    }

    private boolean hasSame(int numOfMatching) {
        return this.numOfMatching == numOfMatching;
    }

    public static List<Rank> valuesWithoutMiss() {
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
