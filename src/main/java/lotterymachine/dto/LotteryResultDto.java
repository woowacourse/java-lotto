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
        List<LotteryResultDto> winningLotteries = new ArrayList<>();
        for (Entry<WinningLottery, Count> ticket : lotteryTickets.entrySet()) {
            addWinningLottery(winningLotteries, ticket);
        }
        return winningLotteries;
    }

    private static void addWinningLottery(List<LotteryResultDto> winningLotteries, Entry<WinningLottery, Count> ticket) {
        WinningLottery winningLottery = ticket.getKey();
        if (!winningLottery.equals(WinningLottery.INVALID)) {
            Count countOfMatchingNumbers = Count.from(winningLottery.getNumber());
            Money winningPrice = Money.from(winningLottery.getPrice());
            boolean bonus = winningLottery.isBonus();
            winningLotteries.add(new LotteryResultDto(countOfMatchingNumbers, winningPrice, ticket.getValue(), bonus));
        }
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
