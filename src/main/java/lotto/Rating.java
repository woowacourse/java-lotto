package lotto;

import java.util.Arrays;
import java.util.List;
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
    // private int count = 0;

    Rating(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static Rating getRating(int matchCount, boolean containBonusBall) {
        if (matchCount == THIRD.matchCount && !containBonusBall) {
            return THIRD;
        }

        if (matchCount < FIFTH.matchCount) {
            return MISS;
        }

        return Arrays.stream(values()).filter(rating -> rating.matchCount == matchCount).findAny()
            .orElseThrow(NoSuchElementException::new);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }
    //    public void plusCount(){
//        this.count++;
//    }

    // Rating rating = Rating.getRating(6,false)
    // rating.plusCount();
}
