package lotto.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE(2_000_000_000, (val, isBonus) -> val == 6),
    SECOND_PLACE(30_000_000, (val, isBonus) -> val == 5 && isBonus),
    THIRD_PLACE(1_500_000, (val, isBonus) -> val == 5 && !isBonus),
    FOURTH_PLACE(50_000, (val, isBonus) -> val == 4),
    FIFTH_PLACE(5_000, (val, isBonus) -> val == 3),
    UNRANKED(0, (val, isBonus) -> val < 3);

    private final BiFunction<Integer, Boolean, Boolean> expression;
    private final BigDecimal prize;

    Rank(final long prize, final BiFunction<Integer, Boolean, Boolean> expression) {
        this.prize = BigDecimal.valueOf(prize);
        this.expression = expression;
    }

    public static Rank getInstance(int val, boolean isBonus) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.expression.apply(val, isBonus))
            .findFirst()
            .orElse(UNRANKED);
    }

    public BigDecimal getPrize() {
        return this.prize;
    }

}