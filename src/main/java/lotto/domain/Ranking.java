package lotto.domain;

import static lotto.domain.Ranking.Constants.FIFTH_PLACE_HIT_COUNT;
import static lotto.domain.Ranking.Constants.FIRST_PLACE_HIT_COUNT;
import static lotto.domain.Ranking.Constants.FOURTH_PLACE_HIT_COUNT;
import static lotto.domain.Ranking.Constants.SECOND_THIRD_PLACE_HIT_COUNT;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Ranking {
    FIRST_PLACE(2000000000, FIRST_PLACE_HIT_COUNT, (hitCount1, hasBonusNumber) -> hitCount1 == FIRST_PLACE_HIT_COUNT),
    SECOND_PLACE(30000000, SECOND_THIRD_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == SECOND_THIRD_PLACE_HIT_COUNT && hasBonusNumber),
    THIRD_PLACE(1500000, SECOND_THIRD_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == SECOND_THIRD_PLACE_HIT_COUNT && !hasBonusNumber),
    FOURTH_PLACE(50000, FOURTH_PLACE_HIT_COUNT, (hitCount, hasBonusNumber) -> hitCount == FOURTH_PLACE_HIT_COUNT),
    FIFTH_PLACE(5000, FIFTH_PLACE_HIT_COUNT, (hitCount, hasBonusNumber) -> hitCount == FIFTH_PLACE_HIT_COUNT),
    NONE_PLACE(0, 0, (hitCount, hasBonusNumber) -> hitCount < FIFTH_PLACE_HIT_COUNT);

    private final int prize;
    private final int hitCount;
    private final BiPredicate<Integer, Boolean> biPredicate;

    Ranking(int prize, int count, BiPredicate<Integer, Boolean> biPredicate) {
        this.prize = prize;
        this.hitCount = count;
        this.biPredicate = biPredicate;
    }

    public static Ranking of(int hitCount, boolean hasBonusNumber) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking.biPredicate.test(hitCount, hasBonusNumber))
                .findFirst()
                .get();
    }

    public long multiplyPrizeWithCount(int count) {
        return (long) prize * count;
    }

    public boolean isSecond() {
        return this == SECOND_PLACE;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getPrize() {
        return prize;
    }

    static class Constants {
        static final int FIRST_PLACE_HIT_COUNT = 6;
        static final int SECOND_THIRD_PLACE_HIT_COUNT = 5;
        static final int FOURTH_PLACE_HIT_COUNT = 4;
        static final int FIFTH_PLACE_HIT_COUNT = 3;
    }
}
