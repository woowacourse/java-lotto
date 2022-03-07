package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.domain.LotteryPurchaseCount;
import lotterymachine.domain.LotteryPurchaseMoney;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;


public class LotteryMachineApplication {
    public static void main(String[] args) {
        LotteryPurchaseMoney lotteryPurchaseMoney = new LotteryPurchaseMoney(InputView.getAmount());
        LotteryPurchaseCount lotteryPurchaseCount = createCount(lotteryPurchaseMoney);
        LotteryTickets lotteryTickets = createLotteryTickets(lotteryPurchaseCount);
        OutputView.printLotteryPurchaseCount(lotteryPurchaseCount);
        OutputView.printLotteryTickets(lotteryTickets);

        WinningLottery winningLottery = new WinningLottery(LotteryTicket.from(InputView.getWinningLotteryNumbers())
                , LotteryNumber.from(InputView.getBonusNumber()));
        WinningResult winningResult = WinningResult.create(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(Yield.of(winningResult.findTotalProfit(), lotteryPurchaseMoney.getValue()).getProfitRate());
    }

    private static LotteryPurchaseCount createCount(LotteryPurchaseMoney lotteryPurchaseMoney) {
        int passivityPurchaseCount = InputView.getPassivityPurchaseCount();
        return new LotteryPurchaseCount(passivityPurchaseCount, lotteryPurchaseMoney.getPurchasePossibleCount() - passivityPurchaseCount, lotteryPurchaseMoney.getPurchasePossibleCount());
    }

    private static LotteryTickets createLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount) {
        List<List<Integer>> passivityLotteryNumbers = InputView.getPassivityLotteryNumbers(lotteryPurchaseCount.getPassivityValue());
        List<LotteryTicket> passivityTickets = LotteryTicket.createLotteryTickets(passivityLotteryNumbers);
        List<LotteryTicket> autoTickets = LotteryTicket.createAutoLotteryTickets(lotteryPurchaseCount.getAutoValue()
                , new RandomLotteryNumbersGenerator());

        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        lotteryTickets.addAll(passivityTickets);
        lotteryTickets.addAll(autoTickets);

        return new LotteryTickets(lotteryTickets, lotteryPurchaseCount);
    }
}