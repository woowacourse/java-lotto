package lotterymachine;

import lotterymachine.domain.LotteryController;
import lotterymachine.domain.WinningLotteryNumbers;
import lotterymachine.domain.WinningLottery;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;
import lotterymachine.vo.LotteryPurchase;

import java.util.Map;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;

public class LotteryMachine {
    public static void main(String[] args) {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(InputView.getAmount());
        int purchaseCount = lotteryPurchase.getCount();
        OutputView.printNumberOfTicket(purchaseCount);

        LotteryController lotteryController = createLotteryTickets(purchaseCount);
        OutputView.printLotteryTickets(lotteryController.getLotteryTickets());
        
        Map<WinningLottery, Integer> lotteryTicketResult = getWinningLotteryResult(lotteryController);
        OutputView.printWinningLotteryResults(lotteryTicketResult);
        OutputView.printProfitRate(getTotalProfitRate(lotteryPurchase, lotteryTicketResult));
    }

    private static LotteryController createLotteryTickets(int purchaseCount) {
        LotteryController lotteryController = new LotteryController(purchaseCount);
        lotteryController.createLotteryTickets(purchaseCount);
        return lotteryController;
    }

    private static Map<WinningLottery, Integer> getWinningLotteryResult(LotteryController lotteryController) {
        WinningLotteryNumbers winningLotteryNumbers =  new WinningLotteryNumbers(InputView.getWinningLotteryNumbers(), InputView.getBonusNumber());
        return lotteryController.getLotteryTicketResult(winningLotteryNumbers);
    }

    private static double getTotalProfitRate(LotteryPurchase lotteryPurchase, Map<WinningLottery, Integer> lotteryTicketResult) {
        int totalProfit = LotteryCalculator.totalProfit(lotteryTicketResult);
        return calculateProfitRate(totalProfit, lotteryPurchase.getPurchasePrice());
    }
}
