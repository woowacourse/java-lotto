package lotterymachine;

import lotterymachine.domain.*;
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
        int manualPurchaseCount = InputView.getManualPurchaseCount();
        return new LotteryPurchaseCount(manualPurchaseCount,
                lotteryPurchaseMoney.getPurchasePossibleCount() - manualPurchaseCount
                , lotteryPurchaseMoney.getPurchasePossibleCount());
    }

    private static LotteryTickets createLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount) {
        List<LotteryTicket> manualLotteryTickets = createManualLotteryTickets(lotteryPurchaseCount);
        List<LotteryTicket> autoLotteryTickets = createAutoLotteryTickets(lotteryPurchaseCount);

        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        lotteryTickets.addAll(manualLotteryTickets);
        lotteryTickets.addAll(autoLotteryTickets);

        return new LotteryTickets(lotteryTickets, lotteryPurchaseCount);
    }

    private static List<LotteryTicket> createManualLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount) {
        List<List<Integer>> manualLotteryNumbers = InputView.getManualLotteryNumbers(lotteryPurchaseCount.getManualValue());
        return LotteryTicket.createLotteryTickets(manualLotteryNumbers);
    }

    private static List<LotteryTicket> createAutoLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount) {
        AutoLotteryTicketGenerator autoLotteryTicketGenerator = new AutoLotteryTicketGenerator(
                lotteryPurchaseCount.getAutoValue()
                , new RandomLotteryNumbersGenerator());
        return autoLotteryTicketGenerator.createLotteryTickets();
    }
}