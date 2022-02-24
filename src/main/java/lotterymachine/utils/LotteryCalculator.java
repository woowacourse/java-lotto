package lotterymachine.utils;

import static java.lang.Math.floor;

public class LotteryCalculator {
    private static final int LOTTERY_PRICE = 1000;
    private static final double DECIMAL_PLACE_SAVER = 100.0;

    public static int divideByLotteryPrice(int amount) {
        return amount / LOTTERY_PRICE;
    }

    public static double calculateProfitRate(double winningLotteryAmount, int amount) {
        return floor(winningLotteryAmount / amount * DECIMAL_PLACE_SAVER)  / DECIMAL_PLACE_SAVER;
    }
}
