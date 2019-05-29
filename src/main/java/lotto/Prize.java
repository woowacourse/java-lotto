package lotto;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Prize {
    FIRST(6),
    SECOND(5),
    THIRD(4),
    FOURTH(3),
    MISS(0);

    private int matchCount;

    Prize(int matchCount) {
        this.matchCount = matchCount;
    }

    public static Prize getPrizeRank(int matchCount) {
        try {
            return Arrays.stream(values()).filter(prize -> prize.matchCount == matchCount).findAny().get();
        } catch (NoSuchElementException e) {
            return MISS;
        }
    }
}
