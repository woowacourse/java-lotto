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
        Count count = new Count(money);
        int passivityLotteryCount = InputView.getPassivityPurchaseCount();

        LotteryTickets lotteryTickets = createLotteryTickets(count, passivityLotteryCount);


        int autoCount = count.getValue() - passivityLotteryCount;
        OutputView.printLotteryPurchaseCount(passivityLotteryCount, autoCount);


//
//        OutputView.printLotteryPurchase(lotteryPurchase);
//        LotteryPurchase lotteryPurchase = LotteryConverter.createLotteryPurchase();
//        LotteryTickets lotteryTickets = LotteryConverter.createLotteryTickets(lotteryPurchase);
//        OutputView.printLotteryTickets(lotteryTickets);
//
//        WinningLottery winningLottery = LotteryConverter.createWinningLottery();
//        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
//        OutputView.printWinningLotteryResults(winningResult.getResult());
//        OutputView.printProfitRate(winningResult.getTotalProfitRate(lotteryPurchase));
    }

    private static LotteryTickets createLotteryTickets(Count count, int passivityLotteryCount) {
        List<LotteryTicket> passivityLotteryTickets = createPassivityLotteryTickets(passivityLotteryCount);
        List<LotteryTicket> autoLotteryTickets = createAutoLotteryTickets(count.getValue() - passivityLotteryCount);
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

    private static List<LotteryTicket> createPassivityLotteryTickets(int passivityLotteryCount) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < passivityLotteryCount; i++) {
            List<LotteryNumber> lotteryNumbers = LotteryNumber.from(InputView.getPassivityLotteryTicket2());
            LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
            lotteryTickets.add(lotteryTicket);
        }
        return lotteryTickets;
    }
}