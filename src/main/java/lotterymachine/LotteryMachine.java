package lotterymachine;

import lotterymachine.domain.LotteryMachineController;
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
        int tickPurchaseCount = lotteryPurchase.getCount();
        OutputView.printNumberOfTicket(tickPurchaseCount);
        LotteryMachineController lotteryMachineController = new LotteryMachineController(tickPurchaseCount);
        lotteryMachineController.createLotteryTickets(tickPurchaseCount);
        OutputView.printLotteryTickets(lotteryMachineController.getLotteryTickets());
        WinningLotteryNumbers winningLotteryNumbers =  new WinningLotteryNumbers(InputView.getWinningLotteryNumbers(), InputView.getBonusNumber());
        Map<WinningLottery, Integer> lotteryTicketResult = lotteryMachineController.getLotteryTicketResult(winningLotteryNumbers);
        OutputView.printWinningLotteryResults(lotteryTicketResult);
        OutputView.printProfitRate(LotteryCalculator.calculateProfitRate(lotteryMachineController.totalProfit(lotteryTicketResult), lotteryPurchase.getPurchasePrice()));
    }
}
