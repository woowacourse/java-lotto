package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;


public class LotteryMachineApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.getAmount());
        int passivityLotteryCount = InputView.getPassivityPurchaseCount();

        List<LotteryTicket> passivityLotteryTickets = createPassivityLotteryTickets(passivityLotteryCount);



        LotteryPurchase lotteryPurchase = LotteryConverter.createLotteryPurchase();
        LotteryTickets lotteryTickets = LotteryConverter.createLotteryTickets(lotteryPurchase);
        OutputView.printLotteryPurchase(lotteryPurchase);
        OutputView.printLotteryTickets(lotteryTickets);

        WinningLottery winningLottery = LotteryConverter.createWinningLottery();
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(winningResult.getTotalProfitRate(lotteryPurchase));
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