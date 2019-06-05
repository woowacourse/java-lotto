package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6),
    THIRD(5),
    FOURTH(4),
    FIFTH(3),
    MISS(0);

    private int numberOfMatching;

    Rank (int numberOfMathcin) {
        this.numberOfMatching = numberOfMathcin;
    }

    public static Rank of(int numberOfMatching) {
        return Arrays.stream(values())
                .filter(rank -> rank.hasSame(numberOfMatching))
                .findFirst()
                .orElse(MISS);
    }

    private boolean hasSame(int numberOfMatching) {
        return this.numberOfMatching == numberOfMatching;
    }
}
