package lotterymachine.controller;

import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.model.WinningLottery;
import lotterymachine.utils.LotteryNumbersGenerator;

import java.util.List;
import java.util.Map;

public class LotteryMachineController {
    private final LotteryTickets lotteryTickets;

    public LotteryMachineController(int number) {
        lotteryTickets = new LotteryTickets(number);
    }

    public void createLotteryTickets(int count) {
        for (int i = 0; i < count; i++) {
            lotteryTickets.add(LotteryNumbersGenerator.generate());
        }
    }

    public Map<WinningLottery, Integer> getLotteryTicketResult(List<Integer> numbers, int bonusNumber) {
        return lotteryTickets.getLotteriesResult(numbers, bonusNumber);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return lotteryTickets.getLotteryTickets();
    }

    public int totalProfit(Map<WinningLottery, Integer> lotteryTicketResult) {
        int sum = 0;
        for (WinningLottery winningLottery: lotteryTicketResult.keySet()) {
            sum += winningLottery.getPrice() * lotteryTicketResult.get(winningLottery);
        }
        return sum;
    }
}
