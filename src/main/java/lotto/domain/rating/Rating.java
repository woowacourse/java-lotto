package lotto.domain.rating;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rating {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private int matchCount;
    private int reward;

    Rating(final int matchCount, final int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rating getRating(final int matchCount, final boolean containBonusBall) {
        if (matchCount == THIRD.matchCount && !containBonusBall) {
            return THIRD;
        }

        if (matchCount < FIFTH.matchCount) {
            return MISS;
        }

        return Arrays.stream(values())
                     .filter(rating -> rating.matchCount == matchCount)
                     .findAny()
                     .orElseThrow(NoSuchElementException::new);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

}
