package lotterymachine.dto;

import java.util.Map.Entry;
import lotterymachine.vo.Count;
import lotterymachine.vo.Money;
import lotterymachine.model.WinningLottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotteryResultDto implements Comparable<LotteryResultDto> {
    private final Count countOfMatchingNumbers;
    private final Money winningPrice;
    private final Count numberOfMatchingTicket;
    private final boolean bonus;

    private LotteryResultDto(Count countOfMatchingNumbers, Money winningPrice, Count count, boolean bonus) {
        this.countOfMatchingNumbers = countOfMatchingNumbers;
        this.winningPrice = winningPrice;
        this.numberOfMatchingTicket = count;
        this.bonus = bonus;
    }

    public static List<LotteryResultDto> createLotteryResults(Map<WinningLottery, Count> lotteryTickets) {
        List<LotteryResultDto> lotteryResults = new ArrayList<>();
        for (Entry<WinningLottery, Count> ticket : lotteryTickets.entrySet()) {
            addWinningLottery(lotteryResults, ticket);
        }
        return lotteryResults;
    }

    private static void addWinningLottery(List<LotteryResultDto> lotteryResults, Entry<WinningLottery, Count> ticket) {
        WinningLottery winningType = ticket.getKey();
        if (winningType.equals(WinningLottery.INVALID)) {
            return;
        }
        Count matchingBalls = Count.from(winningType.getNumber());
        Money winningPrice = Money.from(winningType.getPrice());
        boolean bonus = winningType.isBonus();
        Count matchingLotteryTickets = ticket.getValue();
        lotteryResults.add(new LotteryResultDto(matchingBalls, winningPrice, matchingLotteryTickets, bonus));
    }

    public int getCountOfMatchingNumbers() {
        return countOfMatchingNumbers.getNumber();
    }

    public int getWinningPrice() {
        return winningPrice.getAmount();
    }

    public int getNumberOfMatchingTicket() {
        return this.numberOfMatchingTicket.getNumber();
    }

    public boolean isBonus() {
        return bonus;
    }

    @Override
    public int compareTo(LotteryResultDto o) {
        return this.winningPrice.getAmount() - o.winningPrice.getAmount();
    }
}
