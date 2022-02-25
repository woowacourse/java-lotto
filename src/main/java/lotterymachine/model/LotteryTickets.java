package lotterymachine.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LotteryTickets {
    private final List<LotteryTicket> tickets;

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.tickets = lotteryTickets;
    }

    public Map<WinningLottery, Count> getLotteriesResult(List<Integer> numbers, int bonusNumber) {
        final Map<WinningLottery, Count> lotteriesResult = WinningLottery.getWinningLotteries();
        for (LotteryTicket lotteryTicket : tickets) {
            int matchingNumbers = lotteryTicket.countMatchingNumbers(numbers);
            boolean containsBonus = lotteryTicket.containsNumber(bonusNumber);
            WinningLottery winningLottery = WinningLottery.find(containsBonus, matchingNumbers);
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
