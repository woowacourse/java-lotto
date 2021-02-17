package lotto.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE((val, isBonus) -> val == 6),
    SEC0ND_PLACE((val, isBonus) -> val == 5 && isBonus),
    THIRD_PLACE((val, isBonus) -> val == 5 && !isBonus),
    FOURTH_PLACE((val, isBonus) -> val == 4),
    FIFTH_PLACE((val, isBonus) -> val == 3),
    UNRANKED((val, isBonus) -> val < 3);

    private final BiFunction<Integer, Boolean, Boolean> expression;

    Rank(BiFunction<Integer, Boolean, Boolean> expression) {
        this.expression = expression;
    }

    public static Rank getInstance(int val, boolean isBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.expression.apply(val, isBonus))
            .findFirst()
            .orElse(UNRANKED);
    }

}