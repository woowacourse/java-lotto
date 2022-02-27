package lotterymachine;

import lotterymachine.domain.LotteryController;
import lotterymachine.domain.WinningLotteryNumbers;
import lotterymachine.domain.WinningLottery;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;
import lotterymachine.vo.LotteryPurchase;

import java.util.Map;

public class LotteryMachine {
    public static void main(String[] args) {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(InputView.getAmount());
        int purchaseCount = lotteryPurchase.getCount();
        OutputView.printNumberOfTicket(purchaseCount);

        LotteryController lotteryController = new LotteryController(purchaseCount);
        lotteryController.createLotteryTickets(purchaseCount);
        OutputView.printLotteryTickets(lotteryController.getLotteryTickets());

        WinningLotteryNumbers winningLotteryNumbers =  new WinningLotteryNumbers(InputView.getWinningLotteryNumbers(), InputView.getBonusNumber());
        Map<WinningLottery, Integer> lotteryTicketResult = lotteryController.getLotteryTicketResult(winningLotteryNumbers);
        OutputView.printWinningLotteryResults(lotteryTicketResult);
        OutputView.printProfitRate(LotteryCalculator.calculateProfitRate(lotteryController.totalProfit(lotteryTicketResult), lotteryPurchase.getPurchasePrice()));
    }
}
