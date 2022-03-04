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

    public Map<WinningLottery, Count> getLotteriesResult(LotteryTicket ticket, Ball bonus) {
        final Map<WinningLottery, Count> lotteriesResult = WinningLottery.getWinningLotteries();
        for (LotteryTicket lotteryTicket : tickets) {
            int numberOfMatchingBalls = lotteryTicket.countMatchingBalls(ticket);
            boolean containsBonus = lotteryTicket.contains(bonus);
            WinningLottery winningLottery = WinningLottery.find(containsBonus, numberOfMatchingBalls);
            lotteriesResult.get(winningLottery).increase();
        }
        return Collections.unmodifiableMap(lotteriesResult);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
