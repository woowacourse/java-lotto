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
    private boolean bonus;

    private LotteryResultDto(WinningLottery winningLottery, Count count) {
        this.countOfMatchingNumbers = Count.from(winningLottery.getNumber());
        this.winningPrice = Money.from(winningLottery.getPrice());
        this.numberOfMatchingTicket = count;
        if (winningLottery.equals(WinningLottery.BONUS_FIVE)) {
            this.bonus = true;
        }
    }

    public static List<LotteryResultDto> createLotteryResults(Map<WinningLottery, Count> lotteryTickets) {
        List<LotteryResultDto> winningLotteries = new ArrayList<>();
        for (Entry<WinningLottery, Count> ticket : lotteryTickets.entrySet()) {
            addWinningLottery(winningLotteries, ticket);
        }
        return winningLotteries;
    }

    private static void addWinningLottery(List<LotteryResultDto> winningLotteries, Entry<WinningLottery, Count> ticket) {
        if (!ticket.getKey().equals(WinningLottery.INVALID)) {
            winningLotteries.add(new LotteryResultDto(ticket.getKey(), ticket.getValue()));
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
