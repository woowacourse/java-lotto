package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Ranking {
    FIRST_PLACE(2000000000, Constants.FIRST_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == Constants.FIRST_PLACE_HIT_COUNT),
    SECOND_PLACE(30000000, Constants.SECOND_THIRD_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == Constants.SECOND_THIRD_PLACE_HIT_COUNT && hasBonusNumber),
    THIRD_PLACE(1500000, Constants.SECOND_THIRD_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == Constants.SECOND_THIRD_PLACE_HIT_COUNT && !hasBonusNumber),
    FOURTH_PLACE(50000, Constants.FOURTH_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == Constants.FOURTH_PLACE_HIT_COUNT),
    FIFTH_PLACE(5000, Constants.FIFTH_PLACE_HIT_COUNT,
            (hitCount, hasBonusNumber) -> hitCount == Constants.FIFTH_PLACE_HIT_COUNT),
    NONE_PLACE(0, 0, (hitCount, hasBonusNumber) -> hitCount < Constants.FIFTH_PLACE_HIT_COUNT);

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

    public static List<Ranking> valuesWithoutDefault() {
        return Arrays.stream(Ranking.values()).filter(ranking -> ranking != Ranking.NONE_PLACE)
                .collect(Collectors.toList());
    }

    private static class Constants {
        private static final int FIRST_PLACE_HIT_COUNT = 6;
        private static final int SECOND_THIRD_PLACE_HIT_COUNT = 5;
        private static final int FOURTH_PLACE_HIT_COUNT = 4;
        private static final int FIFTH_PLACE_HIT_COUNT = 3;
    }
}
