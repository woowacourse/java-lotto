package lotterymachine.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.tickets = lotteryTickets;
    }

    public Map<WinningLottery, Count> getLotteriesResult(List<Ball> balls, Ball bonus) {
        final Map<WinningLottery, Count> lotteriesResult = WinningLottery.getWinningLotteries();
        for (LotteryTicket lotteryTicket : tickets) {
            int numberOfMatchingBalls = lotteryTicket.countMatchingBalls(balls);
            boolean containsBonus = lotteryTicket.contains(bonus);
            WinningLottery winningLottery = WinningLottery.find(containsBonus, numberOfMatchingBalls);
            addWinningLottery(lotteriesResult, winningLottery);
        }
        return Collections.unmodifiableMap(lotteriesResult);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }

    private void addWinningLottery(Map<WinningLottery, Count> lotteriesResult, WinningLottery winningLottery) {
        if (winningLottery != null) {
            lotteriesResult.put(winningLottery, lotteriesResult.get(winningLottery).increase());
        }
    }
}
