package lotterymachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private static final Map<WinningLotteryRank, Integer> result = new EnumMap<>(WinningLotteryRank.class);

    private WinningResult() {
    }

    public static WinningResult create(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        initResult();
        for (LotteryTicket lotteryTicket : lotteryTickets.getLotteryTickets()) {
            WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);
            result.put(winningLotteryRank, result.get(winningLotteryRank) + 1);
        }
        return new WinningResult();
    }

    private static void initResult() {
        for (WinningLotteryRank winningLotteryRank : WinningLotteryRank.values()) {
            result.put(winningLotteryRank, 0);
        }
    }

    public Map<WinningLotteryRank, Integer> getResult() {
        return result;
    }

    public int findTotalProfit() {
        int sum = 0;
        for (WinningLotteryRank winningLotteryRank : result.keySet()) {
            sum += winningLotteryRank.getPrice() * result.get(winningLotteryRank);
        }
        return sum;
    }
}