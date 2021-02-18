package lotto.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST_PLACE(BigDecimal.valueOf(2000000000), (val, isBonus) -> val == 6),
    SEC0ND_PLACE(BigDecimal.valueOf(30000000), (val, isBonus) -> val == 5 && isBonus),
    THIRD_PLACE(BigDecimal.valueOf(1500000), (val, isBonus) -> val == 5 && !isBonus),
    FOURTH_PLACE(BigDecimal.valueOf(50000), (val, isBonus) -> val == 4),
    FIFTH_PLACE(BigDecimal.valueOf(5000), (val, isBonus) -> val == 3),
    UNRANKED(BigDecimal.valueOf(0), (val, isBonus) -> val < 3);

    private final BiFunction<Integer, Boolean, Boolean> expression;
    private final BigDecimal prize;

    Rank(BigDecimal prize, BiFunction<Integer, Boolean, Boolean> expression) {
        this.prize = prize;
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