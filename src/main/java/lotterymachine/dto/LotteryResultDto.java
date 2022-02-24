package lotterymachine.dto;

import lotterymachine.model.WinningLottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotteryResultDto implements Comparable<LotteryResultDto> {
    private final int countOfMatchingNumbers;
    private final int winningPrice;
    private final int numberOfMatchingTicket;
    private boolean bonus;

    public LotteryResultDto(WinningLottery winningLottery, int count) {
        this.countOfMatchingNumbers = winningLottery.getNumber();
        this.winningPrice = winningLottery.getPrice();
        this.numberOfMatchingTicket = count;
        if (winningLottery.equals(WinningLottery.BONUS_FIVE)) {
            this.bonus = true;
        }
    }

    public static List<LotteryResultDto> createList(Map<WinningLottery, Integer> lotteryTickets) {
        List<LotteryResultDto> winningLotteries = new ArrayList<>();
        lotteryTickets.keySet().forEach(winningLottery -> winningLotteries
                .add(new LotteryResultDto(winningLottery, lotteryTickets.get(winningLottery))));
        return winningLotteries;
    }

    public int getCountOfMatchingNumbers() {
        return countOfMatchingNumbers;
    }

    public int getWinningPrice() {
        return winningPrice;
    }

    public int getNumberOfMatchingTicket() {
        return numberOfMatchingTicket;
    }

    public boolean isBonus() {
        return bonus;
    }

    public int sumIncome() {
        return winningPrice * numberOfMatchingTicket;
    }

    @Override
    public int compareTo(LotteryResultDto o) {
        return this.winningPrice - o.winningPrice;
    }
}
