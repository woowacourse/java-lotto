package lotterymachine.domain;

import java.util.*;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;
    private final TicketCount ticketCount;

    public LotteryTickets(int count) {
        tickets = new ArrayList<>();
        ticketCount = new TicketCount(count);
    }

    public void add(List<LotteryNumber> numbers) {
        if (ticketCount.isExist()) {
            tickets.add(new LotteryTicket(numbers));
            ticketCount.subtract();
        }
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public Map<WinningLotteryRank, Integer> getLotteriesResult(WinningLottery winningLottery) {
        final Map<WinningLotteryRank, Integer> lotteriesResult = WinningLotteryRank.getWinningLotteries();
        for (LotteryTicket lotteryTicket : tickets) {
            WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);
            lotteriesResult.put(winningLotteryRank, lotteriesResult.getOrDefault(winningLotteryRank, 0) + 1);
        }
        return Collections.unmodifiableMap(lotteriesResult);
    }
}
