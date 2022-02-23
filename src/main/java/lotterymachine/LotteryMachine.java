package lotterymachine;

import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.utils.LotteryNumbersGenerator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LotteryMachine {
    public static void main(String[] args) {
        int amount = InputView.getAmount();
        int numberOfTickets = LotteryCalculator.divideByLotteryPrice(amount);
        OutputView.printNumberOfTicket(numberOfTickets);
        LotteryTickets lotteryTickets = new LotteryTickets(createLotteryTickets(amount, numberOfTickets));
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());

    }

    private static List<LotteryTicket> createLotteryTickets(int amount, int numberOfTickets) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            lotteryTickets.add(new LotteryTicket(LotteryNumbersGenerator.generate()));
        }
        return lotteryTickets;
    }
}
