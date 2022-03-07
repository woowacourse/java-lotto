package lotterymachine.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.tickets = lotteryTickets;
    }

    public static LotteryTickets merge(LotteryTickets manualTickets, LotteryTickets autoTickets) {
        return new LotteryTickets(
                Stream.concat(manualTickets.getLotteryTickets().stream(), autoTickets.getLotteryTickets().stream())
                        .collect(Collectors.toUnmodifiableList()));
    }

    public Map<WinningType, Count> getLotteriesResult(LotteryTicket ticket, Ball bonus) {
        final Map<WinningType, Count> lotteriesResult = WinningType.getWinningLotteries();
        for (LotteryTicket lotteryTicket : tickets) {
            int numberOfMatchingBalls = lotteryTicket.countMatchingBalls(ticket);
            boolean containsBonus = lotteryTicket.contains(bonus);
            WinningType winningType = WinningType.find(containsBonus, numberOfMatchingBalls);
            Count increasedCount = lotteriesResult.get(winningType).increase();
            lotteriesResult.put(winningType, increasedCount);
        }
        return Collections.unmodifiableMap(lotteriesResult);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
