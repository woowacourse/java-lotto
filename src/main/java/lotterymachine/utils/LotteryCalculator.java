package lotterymachine.utils;

import lotterymachine.domain.WinningLottery;

import java.util.Map;

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

    public static int totalProfit(Map<WinningLottery, Integer> lotteryTicketResult) {
        int sum = 0;
        for (WinningLottery winningLottery : lotteryTicketResult.keySet()) {
            sum += winningLottery.getPrice() * lotteryTicketResult.get(winningLottery);
        }
        return sum;
    }
}
