package lotterymachine.model;

import java.util.Map;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;

public class LotteryResult {
    private static final double DECIMAL_PLACE_SAVER = 100.0;
    private static final int LOTTERY_PRICE = 1000;

    private final LotteryTickets lotteryTickets;
    private final WinningLottery winningLottery;

    public LotteryResult(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        this.lotteryTickets = lotteryTickets;
        this.winningLottery = winningLottery;
    }

    public Map<WinningType, Count> compute() {
        LotteryTicket winningTicket = winningLottery.getWinningTicket();
        Ball bonusBall = winningLottery.getBonusBall();
        return lotteryTickets.getLotteriesResult(winningTicket, bonusBall);
    }

    public double getProfitRate(final Map<WinningType, Count> ticketsResult) {
        int totalTicketsAmount = lotteryTickets.getLotteryTickets().size() * LOTTERY_PRICE;
        double winningLotteryAmount = getWinningAmount(ticketsResult);
        return Math.floor(winningLotteryAmount / totalTicketsAmount * DECIMAL_PLACE_SAVER) / DECIMAL_PLACE_SAVER;
    }

    private double getWinningAmount(Map<WinningType, Count> ticketsResult) {
        return ticketsResult.entrySet()
                .stream()
                .mapToDouble(ticketResult -> ticketResult.getKey().multiply(ticketResult.getValue()))
                .sum();
    }
}
