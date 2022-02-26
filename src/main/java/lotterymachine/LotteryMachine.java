package lotterymachine;

import lotterymachine.controller.LotteryMachineController;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;
import lotterymachine.vo.LotteryPurchase;


public class LotteryMachine {
    public static void main(String[] args) {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(InputView.getAmount());
        int tickPurchaseCount = lotteryPurchase.getCount();
        LotteryMachineController lotteryMachineController = new LotteryMachineController(tickPurchaseCount);
        lotteryMachineController.createLotteryTickets(tickPurchaseCount);
        OutputView.printNumberOfTicket(tickPurchaseCount);


    }
}
