package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum PrizeType {
    THREE(3, false, "3개 일치 (5000원)- ", (value -> value * 5000)),
    FOUR(4, false, "4개 일치 (50000원)- ", (value -> value * 50000)),
    FIVE(5, false, "5개 일치 (1500000원)- ", (value -> value * 1500000)),
    FIVE_WITH_BONUS(5, true, "5개 일치, 보너스 볼 일치 (30000000원)- ", (value -> value * 30000000)),
    SIX(6, false, "6개 일치 (2000000000원)- ", (value -> value * 2000000000));

    private int type;
    private boolean isBonus;
    private String result;
    private Function<Integer, Integer> expression;

    PrizeType(int type, boolean isBonus, String result, Function<Integer, Integer> expression) {
        this.type = type;
        this.isBonus = isBonus;
        this.result = result;
        this.expression = expression;
    }

    public int calculatePrizeMoney(int count) {
        return expression.apply(count);
    }

    public static PrizeType getPrizeTypeForWinningCount(Integer count, boolean isBonus) {
        return Arrays.stream(PrizeType.values())
                .filter(pt -> pt.type == count && pt.isBonus == isBonus)
                .findFirst()
                .orElse(null);
    }

    public String getResultString(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.result);
        sb.append(Integer.toString(count));
        sb.append("개");
        return sb.toString();
    }
}
