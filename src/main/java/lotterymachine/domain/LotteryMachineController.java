package lotterymachine.domain;

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

    public Map<WinningLottery, Integer> getLotteryTicketResult(WinningLotteryNumbers winningLotteryNumbers) {
        return lotteryTickets.getLotteriesResult(winningLotteryNumbers);
    }

    public List<LotteryTicket> getLotteryTickets() {
        return lotteryTickets.getLotteryTickets();
    }

    public int totalProfit(Map<WinningLottery, Integer> lotteryTicketResult) {
        int sum = 0;
        for (WinningLottery winningLottery : lotteryTicketResult.keySet()) {
            sum += winningLottery.getPrice() * lotteryTicketResult.get(winningLottery);
        }
        return sum;
    }
}
