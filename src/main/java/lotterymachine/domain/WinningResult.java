package lotterymachine.domain;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.floor;

public class WinningResult {
    private static final Map<WinningLotteryRank, Integer> result = new EnumMap<>(WinningLotteryRank.class);
    private static final double DECIMAL_PLACE_SAVER = 100.0;

    public static WinningResult create(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        initResult();
        for (LotteryTicket lotteryTicket : lotteryTickets.getLotteryTickets()) {
            WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);
            result.put(winningLotteryRank, result.get(winningLotteryRank) + 1);
        }
        return new WinningResult();
    }

    private WinningResult() {}

    private static void initResult() {
        for (WinningLotteryRank winningLotteryRank : WinningLotteryRank.values()) {
            result.put(winningLotteryRank, 0);
        }
    }

    public Map<WinningLotteryRank, Integer> getResult() {
        return result;
    }

    public double getTotalProfitRate(int number) {
        int totalProfit = totalProfit();
        return calculateProfitRate(totalProfit, number);
    }

    private double calculateProfitRate(double winningLotteryAmount, int amount) {
        return floor(winningLotteryAmount / amount * DECIMAL_PLACE_SAVER) / DECIMAL_PLACE_SAVER;
    }

    private int totalProfit() {
        int sum = 0;
        for (WinningLotteryRank winningLotteryRank : result.keySet()) {
            sum += winningLotteryRank.getPrice() * result.get(winningLotteryRank);
        }
        return sum;
    }
}