package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum PrizeType {
    THREE(3, false, (value -> value * 5000)),
    FOUR(4, false, (value -> value * 50000)),
    FIVE(5, false, (value -> value * 1500000)),
    FIVE_WITH_BONUS(5, true, (value -> value * 30000000)),
    SIX(6, false, (value -> value * 2000000000));

    private int type;
    private boolean isBonus;
    private Function<Integer, Integer> expression;

    PrizeType(int type, boolean isBonus, Function<Integer, Integer> expression) {
        this.type = type;
        this.isBonus = isBonus;
        this.expression = expression;
    }

    public int calculatePrizeMoney(int count) {
        return expression.apply(count);
    }

    public static PrizeType getPrizeTypeForWinningCount(Integer count, boolean isBonus) {
        return Arrays.stream(PrizeType.values())
                .filter(pt -> pt.type == count && pt.isBonus == isBonus)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
