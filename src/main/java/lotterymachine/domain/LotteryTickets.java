package lotterymachine.domain;

import java.util.*;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;
    private final TicketCount ticketCount;

    public LotteryTickets(int count) {
        tickets = new ArrayList<>();
        ticketCount = new TicketCount(count);
    }

    public Map<WinningLottery, Integer> getLotteriesResult(WinningLotteryNumbers winningLotteryNumbers) {
        final Map<WinningLottery, Integer> lotteriesResult = WinningLottery.getWinningLotteries();
        for (LotteryTicket lotteryTicket : tickets) {
            int matchingNumbers = lotteryTicket.countMatchingNumbers(winningLotteryNumbers.getNumbers());
            boolean containsBonus = lotteryTicket.containsNumber(winningLotteryNumbers.getBonusNumber());
            WinningLottery winningLottery = WinningLottery.find(matchingNumbers, containsBonus);
            lotteriesResult.put(winningLottery, lotteriesResult.getOrDefault(winningLottery, 0) + 1);
        }
        return Collections.unmodifiableMap(lotteriesResult);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public void add(List<Integer> numbers) {
        if (ticketCount.isExistCount()) {
            tickets.add(new LotteryTicket(numbers));
            ticketCount.subtract();
        }
    }
}
