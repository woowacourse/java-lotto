package lotterymachine.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryTickets {
    private final List<LotteryTicket> lotteryTickets;

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public int countMatchingNumberOfThree(List<Integer> winningNumbers) {
        int count = 0;
        for (LotteryTicket lotteryTicket : lotteryTickets) {
            if (lotteryTicket.countMatchingNumbers(winningNumbers) == 3) {
                count++;
            }
        }
        return count;
    }

    public int countMatchingNumberOfFour(List<Integer> winningNumbers, int findMatch) {
        int count = 0;
        for (LotteryTicket lotteryTicket : lotteryTickets) {
            if (lotteryTicket.countMatchingNumbers(winningNumbers) == findMatch) {
                count++;
            }
        }
        return count;
    }

    public Map<WinningLottery, Integer> getWinningLotteries(List<Integer> numbers, int bonusNumber) {
        Map<WinningLottery, Integer> winningLotteries = new HashMap<>();
        for (LotteryTicket lotteryTicket : lotteryTickets) {
            int matchingNumbers = lotteryTicket.countMatchingNumbers(numbers);
            boolean containsBonus = lotteryTicket.containsNumber(bonusNumber);
            WinningLottery winningLottery = getWinningLottery(containsBonus, matchingNumbers);
            int value = winningLotteries.getOrDefault(winningLottery, 0);
            winningLotteries.put(winningLottery, value + 1);
        }
        return winningLotteries;
    }

    private WinningLottery getWinningLottery(boolean bonus, int matchingCnt) {
        if (matchingCnt == 5 && bonus) {
            return WinningLottery.BONUS_FIVE;
        }
        return WinningLottery.find(matchingCnt);
    }
}
