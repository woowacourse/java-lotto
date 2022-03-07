package lotterymachine.dto;

import java.util.Map.Entry;
import lotterymachine.vo.Count;
import lotterymachine.model.WinningType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotteryResultDto implements Comparable<LotteryResultDto> {
    private final int countOfMatchingNumbers;
    private final int winningPrice;
    private final int numberOfMatchingTicket;
    private final boolean bonus;

    private LotteryResultDto(int countOfMatchingNumbers, int winningPrice, int count, boolean bonus) {
        this.countOfMatchingNumbers = countOfMatchingNumbers;
        this.winningPrice = winningPrice;
        this.numberOfMatchingTicket = count;
        this.bonus = bonus;
    }

    public static List<LotteryResultDto> createLotteryResults(Map<WinningType, Count> lotteryTickets) {
        List<LotteryResultDto> lotteryResults = new ArrayList<>();
        for (Entry<WinningType, Count> ticket : lotteryTickets.entrySet()) {
            addWinningLottery(lotteryResults, ticket);
        }
        return lotteryResults;
    }

    private static void addWinningLottery(List<LotteryResultDto> lotteryResults, Entry<WinningType, Count> ticket) {
        WinningType winningType = ticket.getKey();
        if (winningType.equals(WinningType.INVALID)) {
            return;
        }
        int matchingBalls = winningType.getNumber();
        int winningPrice = winningType.getPrice();
        boolean bonus = winningType.isBonus();
        int matchingLotteryTickets = ticket.getValue().getNumber();
        lotteryResults.add(new LotteryResultDto(matchingBalls, winningPrice, matchingLotteryTickets, bonus));
    }

    public int getCountOfMatchingNumbers() {
        return this.countOfMatchingNumbers;
    }

    public int getWinningPrice() {
        return this.winningPrice;
    }

    public int getNumberOfMatchingTicket() {
        return this.numberOfMatchingTicket;
    }

    public boolean isBonus() {
        return bonus;
    }

    @Override
    public int compareTo(LotteryResultDto o) {
        return this.winningPrice - o.winningPrice;
    }
}
