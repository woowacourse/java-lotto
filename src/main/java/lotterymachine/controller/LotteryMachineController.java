package lotterymachine.controller;

import lotterymachine.model.LotteryTickets;
import lotterymachine.utils.LotteryNumbersGenerator;
import lotterymachine.vo.LotteryPurchase;

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
}
