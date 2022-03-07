package lotterymachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private final Map<WinningLotteryRank, Integer> result = new EnumMap<>(WinningLotteryRank.class);

    public WinningResult(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        create(lotteryTickets, winningLottery);
    }

    private void create(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        initResult();
        for (LotteryTicket lotteryTicket : lotteryTickets.getLotteryTickets()) {
            WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);
            this.result.put(winningLotteryRank, this.result.get(winningLotteryRank) + 1);
        }
    }

    private void initResult() {
        for (WinningLotteryRank winningLotteryRank : WinningLotteryRank.values()) {
            this.result.put(winningLotteryRank, 0);
        }
    }

    public Map<WinningLotteryRank, Integer> getResult() {
        return this.result;
    }

    public int findTotalProfit() {
        return this.result.keySet().stream()
                .mapToInt(i -> i.getPrice() * result.get(i))
                .sum();
    }
}