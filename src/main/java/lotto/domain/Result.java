package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class Result {
    private static final double ONE_HUNDRED_PERCENT = 100.0;
    private final Map<Prize, Integer> result;

    public Result(final Map<Prize, Integer> result) {
        this.result = result;
    }

    public double calculateRateOfReturn(final int buyPrice) {
        int sum = 0;
        for (Prize prize : Prize.values()) {
            sum += prize.getWinningAmount() * getCountOfPrize(prize);
        }
        return (double) sum / buyPrice * ONE_HUNDRED_PERCENT;
    }

    public int getCountOfPrize(Prize prize) {
        Integer countOfPrize = result.get(prize);
        if (countOfPrize == null) {
            return 0;
        }
        return countOfPrize;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Result result1 = (Result) o;
        return Objects.equals(result, result1.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
