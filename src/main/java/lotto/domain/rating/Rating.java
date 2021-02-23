package lotto.domain.rating;

import java.util.Arrays;

public enum Rating {
    FIRST(new RatingResult(6, false), 2_000_000_000),
    SECOND(new RatingResult(5, true), 30_000_000),
    THIRD(new RatingResult(5, false), 1_500_000),
    FOURTH(new RatingResult(4, false), 50_000),
    FIFTH(new RatingResult(3, false), 5_000),
    MISS(new RatingResult(0, false), 0);

    private final RatingResult ratingResult;
    private final int reward;

    Rating(final RatingResult ratingResult, final int reward) {
        this.ratingResult = ratingResult;
        this.reward = reward;
    }

    public static Rating getRating(final int matchCount, final boolean hasBonusBall) {
        RatingResult ratingResult = new RatingResult(matchCount, hasBonusBall);
        return Arrays.stream(values())
            .filter(rating -> rating.ratingResult.equals(ratingResult))
            .findAny()
            .orElse(MISS);
    }

    public int getMatchCount() {
        return ratingResult.getMatchCount();
    }

    public int getReward() {
        return reward;
    }

}
