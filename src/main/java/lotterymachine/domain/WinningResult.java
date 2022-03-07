package lotterymachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private static final Map<WinningLotteryRank, Integer> RESULT = new EnumMap<>(WinningLotteryRank.class);

    private WinningResult() {
    }

    public static WinningResult create(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        initResult();
        for (LotteryTicket lotteryTicket : lotteryTickets.getLotteryTickets()) {
            WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);
            RESULT.put(winningLotteryRank, RESULT.get(winningLotteryRank) + 1);
        }
        return new WinningResult();
    }

    private static void initResult() {
        for (WinningLotteryRank winningLotteryRank : WinningLotteryRank.values()) {
            RESULT.put(winningLotteryRank, 0);
        }
    }

    public Map<WinningLotteryRank, Integer> getResult() {
        return RESULT;
    }

    public int findTotalProfit() {
        return RESULT.keySet().stream()
                .mapToInt(i -> i.getPrice() * RESULT.get(i))
                .sum();
    }
}