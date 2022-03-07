package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;


public class LotteryMachineApplication {
    public static void main(String[] args) {
        LotteryPurchaseMoney lotteryPurchaseMoney = new LotteryPurchaseMoney(InputView.getAmount());
        LotteryPurchaseCount lotteryPurchaseCount = createCount(lotteryPurchaseMoney);

        LotteryTickets lotteryTickets = createLotteryTickets(lotteryPurchaseCount);
        OutputView.printLotteryPurchaseCount(lotteryPurchaseCount);
        OutputView.printLotteryTickets(lotteryTickets);

        WinningLottery winningLottery = new WinningLottery(LotteryTicket.from(InputView.getWinningLotteryNumbers())
                , LotteryNumber.from(InputView.getBonusNumber()));
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(Yield.of(winningResult.findTotalProfit(), lotteryPurchaseMoney.getAmount()).getProfitRate());
    }

    private static LotteryPurchaseCount createCount(LotteryPurchaseMoney lotteryPurchaseMoney) {
        int manualPurchaseCount = InputView.getManualPurchaseCount();
        return new LotteryPurchaseCount(manualPurchaseCount,
                lotteryPurchaseMoney.getPurchasePossibleCount() - manualPurchaseCount,
                lotteryPurchaseMoney.getPurchasePossibleCount());
    }

    private static LotteryTickets createLotteryTickets(LotteryPurchaseCount lotteryPurchaseCount) {
        LotteryTicketsController lotteryTicketsController = new LotteryTicketsController();
        return lotteryTicketsController.createLotteryTickets(lotteryPurchaseCount
                , InputView.getManualLotteryNumbers(lotteryPurchaseCount.getManualValue()));
    }
}