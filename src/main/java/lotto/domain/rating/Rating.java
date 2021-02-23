package lotto.domain.rating;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rating {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private static final String NONE_RATING_ERROR_MESSAGE = "[Error] 존재하지 않는 Rating 객체";
    private final int matchCount;
    private final int reward;

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
            .orElseThrow(() -> new NoSuchElementException(NONE_RATING_ERROR_MESSAGE));
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

}
