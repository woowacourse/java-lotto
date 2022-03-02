package lotterymachine.domain;

import lotterymachine.LotteryPurchase;
import lotterymachine.utils.LotteryCalculator;

import java.util.EnumMap;
import java.util.Map;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;

public class WinningResult {
    private final Map<WinningLotteryRank, Integer> result = new EnumMap<>(WinningLotteryRank.class);
    private final LotteryTickets lotteryTickets;
    private final WinningLottery winningLottery;

    public WinningResult(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        this.lotteryTickets = lotteryTickets;
        this.winningLottery = winningLottery;
        createResult();
    }

    private void createResult() {
        initResult();
        for (LotteryTicket lotteryTicket: lotteryTickets.getLotteryTickets()) {
            WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);
            result.put(winningLotteryRank, result.getOrDefault(winningLotteryRank, 0) + 1);
        }
    }

    private void initResult() {
        for (WinningLotteryRank winningLotteryRank: WinningLotteryRank.values()) {
            result.put(winningLotteryRank, 0);
        }
    }

    public Map<WinningLotteryRank, Integer> getResult() {
        return this.result;
    }

    public double getTotalProfitRate(LotteryPurchase lotteryPurchase) {
        int totalProfit = LotteryCalculator.totalProfit(this.result);
        return LotteryCalculator.calculateProfitRate(totalProfit, lotteryPurchase.getTotalAmount());
    }
}
