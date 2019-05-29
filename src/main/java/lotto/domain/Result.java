package lotto.domain;

import java.util.Map;

public class Result {
    public static final int MIN_PRIZE_NUMBER = 3;
    public static final int MAX_PRIZE_NUMBER = 6;
    private static final double ONE_HUNDRED_PERCENT = 100.0;
    private final Map<Prize, Integer> result;

    public Result(final Map<Prize, Integer> result) {
        this.result = result;
    }

    public double calculateRateOfReturn(final int buyPrice) {
        int sum = 0;
        for (int i = MIN_PRIZE_NUMBER; i <= MAX_PRIZE_NUMBER; i++) {
            Prize prize = Prize.valueOf(i);
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
}
