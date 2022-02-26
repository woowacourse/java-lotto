package lotterymachine;

import lotterymachine.controller.LotteryMachineController;
import lotterymachine.model.WinningLottery;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;
import lotterymachine.vo.LotteryPurchase;

import java.util.List;
import java.util.Map;

public class LotteryMachine {
    public static void main(String[] args) {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(InputView.getAmount());
        int tickPurchaseCount = lotteryPurchase.getCount();
        OutputView.printNumberOfTicket(tickPurchaseCount);

        LotteryMachineController lotteryMachineController = new LotteryMachineController(tickPurchaseCount);
        lotteryMachineController.createLotteryTickets(tickPurchaseCount);
        OutputView.printLotteryTickets(lotteryMachineController.getLotteryTickets());

        List<Integer> winningLotteryNumbers =  InputView.getWinningLotteryNumbers();
        int bonusNumber = InputView.getBonusNumber(winningLotteryNumbers);
        Map<WinningLottery, Integer> lotteryTicketResult = lotteryMachineController.getLotteryTicketResult(winningLotteryNumbers, bonusNumber);
        OutputView.printWinningLotteryResults(lotteryTicketResult);
        OutputView.printProfitRate(LotteryCalculator.calculateProfitRate(lotteryMachineController.totalProfit(lotteryTicketResult), lotteryPurchase.getPurchasePrice()));
    }
}
