package lotterymachine.domain;

import lotterymachine.utils.LotteryNumbersGenerator;

import java.util.List;
import java.util.Map;

public class LotteryController {
    private final LotteryTickets lotteryTickets;

    public LotteryController(int number) {
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
}
