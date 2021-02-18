package lotto.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE(2000000000,  (val, isBonus) -> val == 6),
    SEC0ND_PLACE(30000000, (val, isBonus) -> val == 5 && isBonus),
    THIRD_PLACE(1500000, (val, isBonus) -> val == 5 && !isBonus),
    FOURTH_PLACE(50000, (val, isBonus) -> val == 4),
    FIFTH_PLACE(5000, (val, isBonus) -> val == 3),
    UNRANKED(0, (val, isBonus) -> val < 3);

    private final BiFunction<Integer, Boolean, Boolean> expression;
    private final int prize;

    Rank(int prize, BiFunction<Integer, Boolean, Boolean> expression) {
        this.prize = prize;
        this.expression = expression;
    }

    public static Rank getInstance(int val, boolean isBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.expression.apply(val, isBonus))
            .findFirst()
            .orElse(UNRANKED);
    }

    public int getPrize() {
        return this.prize;
    }

}