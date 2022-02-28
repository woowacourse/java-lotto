package lotterymachine;

import lotterymachine.domain.LotteryTickets;
import lotterymachine.domain.WinningLotteryNumbers;
import lotterymachine.domain.WinningLottery;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.utils.LotteryNumbersGenerator;
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

        LotteryTickets lotteryTickets = createLotteryTickets(purchaseCount);
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());

        WinningLotteryNumbers winningLotteryNumbers =  new WinningLotteryNumbers(InputView.getWinningLotteryNumbers(), InputView.getBonusNumber());
        Map<WinningLottery, Integer> lotteryTicketResult = lotteryTickets.getLotteriesResult(winningLotteryNumbers);
        OutputView.printWinningLotteryResults(lotteryTicketResult);
        OutputView.printProfitRate(getTotalProfitRate(lotteryPurchase, lotteryTicketResult));
    }

    private static LotteryTickets createLotteryTickets(int purchaseCount) {
        LotteryTickets lotteryTickets = new LotteryTickets(purchaseCount);
        for (int i = 0; i < purchaseCount; i++) {
            lotteryTickets.add(LotteryNumbersGenerator.generate());
        }
        return lotteryTickets;
    }

    private static double getTotalProfitRate(LotteryPurchase lotteryPurchase, Map<WinningLottery, Integer> lotteryTicketResult) {
        int totalProfit = LotteryCalculator.totalProfit(lotteryTicketResult);
        return calculateProfitRate(totalProfit, lotteryPurchase.getPurchasePrice());
    }
}
