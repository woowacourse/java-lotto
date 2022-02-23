package lotterymachine.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryTickets {
    private final List<LotteryTicket> lotteryTickets;

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public Map<WinningLottery, Integer> getLotteriesResult(List<Integer> numbers, int bonusNumber) {
        Map<WinningLottery, Integer> winningLotteries = new HashMap<>();
        for (LotteryTicket lotteryTicket : lotteryTickets) {
            int matchingNumbers = lotteryTicket.countMatchingNumbers(numbers);
            boolean containsBonus = lotteryTicket.containsNumber(bonusNumber);
            WinningLottery winningLottery = findWinningLottery(containsBonus, matchingNumbers);
            int value = winningLotteries.getOrDefault(winningLottery, 0);
            winningLotteries.put(winningLottery, value + 1);
        }
        return winningLotteries;
    }

    private WinningLottery findWinningLottery(boolean bonus, int matchingCnt) {
        if (matchingCnt == 5 && bonus) {
            return WinningLottery.BONUS_FIVE;
        }
        return WinningLottery.find(matchingCnt);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(lotteryTickets);
    }
}
