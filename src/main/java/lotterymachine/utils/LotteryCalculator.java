package lotterymachine.utils;

import lotterymachine.LotteryPurchase;
import lotterymachine.domain.WinningLotteryRank;

import java.util.Map;

import static java.lang.Math.floor;

public class LotteryCalculator {
    private static final double DECIMAL_PLACE_SAVER = 100.0;

    public static int divideByLotteryPrice(int amount) {
        return amount / LotteryPurchase.PER_LOTTERY_TICKET_PRICE;
    }

    public static double calculateProfitRate(double winningLotteryAmount, int amount) {
        return floor(winningLotteryAmount / amount * DECIMAL_PLACE_SAVER)  / DECIMAL_PLACE_SAVER;
    }

    public static int totalProfit(Map<WinningLotteryRank, Integer> lotteryTicketResult) {
        int sum = 0;
        for (WinningLotteryRank winningLotteryRank : lotteryTicketResult.keySet()) {
            sum += winningLotteryRank.getPrice() * lotteryTicketResult.get(winningLotteryRank);
        }
        return sum;
    }
}
