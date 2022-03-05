package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.utils.LotteryNumbersGenerator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;


public class LotteryMachineApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.getAmount());
        Count count = new Count(InputView.getPassivityPurchaseCount(), money.getPurchasePossibleCount());

        LotteryTickets lotteryTickets = createLotteryTickets(count);
        OutputView.printLotteryPurchaseCount(count);
        OutputView.printLotteryTickets(lotteryTickets);

        WinningLottery winningLottery = new WinningLottery(InputView.getWinningLotteryNumbers(), InputView.getBonusNumber());
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(winningResult.getTotalProfitRate(money));
    }

    private static LotteryTickets createLotteryTickets(Count count) {
        List<LotteryTicket> passivityLotteryTickets = createPassivityLotteryTickets(count.getPassivityValue());
        List<LotteryTicket> autoLotteryTickets = createAutoLotteryTickets(count.getAutoValue());
        return createLotteryTickets(passivityLotteryTickets, autoLotteryTickets);
    }

    private static LotteryTickets createLotteryTickets(List<LotteryTicket> passivityLotteryTickets, List<LotteryTicket> autoLotteryTickets) {
        List<LotteryTicket> tickets = new ArrayList<>();
        tickets.addAll(passivityLotteryTickets);
        tickets.addAll(autoLotteryTickets);
        return new LotteryTickets(tickets);
    }

    private static List<LotteryTicket> createAutoLotteryTickets(int number) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            LotteryTicket lotteryTicket = new LotteryTicket(LotteryNumbersGenerator.generate());
            lotteryTickets.add(lotteryTicket);
        }
        return lotteryTickets;
    }

    private static List<LotteryTicket> createPassivityLotteryTickets(int count) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (List<Integer> numbers:  InputView.getPassivityLotteryTicket(count)) {
            List<LotteryNumber> lotteryNumbers = LotteryNumber.from(numbers);
            lotteryTickets.add(new LotteryTicket(lotteryNumbers));
        }
        return lotteryTickets;
    }
}