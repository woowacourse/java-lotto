package lotterymachine.model;

import java.util.*;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;
    private final TicketCount ticketCount;

    public LotteryTickets(int count) {
        tickets = new ArrayList<>();
        ticketCount = new TicketCount(count);
    }

    public Map<WinningLottery, Integer> getLotteriesResult(List<Integer> numbers, int bonusNumber) {
        final Map<WinningLottery, Integer> lotteriesResult = new EnumMap<>(WinningLottery.class);
        for (LotteryTicket lotteryTicket : tickets) {
            int matchingNumbers = lotteryTicket.countMatchingNumbers(numbers);
            boolean containsBonus = lotteryTicket.containsNumber(bonusNumber);
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
