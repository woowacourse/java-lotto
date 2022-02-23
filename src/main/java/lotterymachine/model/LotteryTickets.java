package lotterymachine.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LotteryTickets {
    private final List<LotteryTicket> lotteryTickets;

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public Map<WinningLottery, Integer> getLotteriesResult(List<Integer> numbers, int bonusNumber) {
        Map<WinningLottery, Integer> lotteriesResult = WinningLottery.getWinningLotteries();
        for (LotteryTicket lotteryTicket : lotteryTickets) {
            int matchingNumbers = lotteryTicket.countMatchingNumbers(numbers);
            boolean containsBonus = lotteryTicket.containsNumber(bonusNumber);
            WinningLottery winningLottery = findWinningLottery(containsBonus, matchingNumbers);
            addWinningLottery(lotteriesResult, winningLottery);
        }
        return lotteriesResult;
    }

    public List<LotteryTicket> getLotteryTickets() {
        return Collections.unmodifiableList(lotteryTickets);
    }

    private WinningLottery findWinningLottery(boolean bonus, int matchingCnt) {
        if (matchingCnt == 5 && bonus) {
            return WinningLottery.BONUS_FIVE;
        }
        return WinningLottery.find(matchingCnt);
    }

    private void addWinningLottery(Map<WinningLottery, Integer> lotteriesResult, WinningLottery winningLottery) {
        if (winningLottery != null) {
            int value = lotteriesResult.getOrDefault(winningLottery, 0);
            lotteriesResult.put(winningLottery, value + 1);
        }
    }
}
