package lotterymachine.dto;

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
    private boolean bonus;

    public LotteryResultDto(WinningLottery winningLottery, Count count) {
        this.countOfMatchingNumbers = new Count(winningLottery.getNumber());
        this.winningPrice = new Money(winningLottery.getPrice());
        this.numberOfMatchingTicket = count;
        if (winningLottery.equals(WinningLottery.BONUS_FIVE)) {
            this.bonus = true;
        }
    }

    public static List<LotteryResultDto> createLotteryResults(Map<WinningLottery, Count> lotteryTickets) {
        List<LotteryResultDto> winningLotteries = new ArrayList<>();
        lotteryTickets.keySet().forEach(winningLottery -> winningLotteries
                .add(new LotteryResultDto(winningLottery, lotteryTickets.get(winningLottery))));
        return winningLotteries;
    }

    public Count getCountOfMatchingNumbers() {
        return countOfMatchingNumbers;
    }

    public int getWinningPrice() {
        return winningPrice.getAmount();
    }

    public Count getNumberOfMatchingTicket() {
        return numberOfMatchingTicket;
    }

    public boolean isBonus() {
        return bonus;
    }

    public Money sumIncome() {
        return new Money(winningPrice.getAmount() * numberOfMatchingTicket.getNumber());
    }

    @Override
    public int compareTo(LotteryResultDto o) {
        return this.winningPrice.getAmount() - o.winningPrice.getAmount();
    }
}
