package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.OutputView;

import java.util.Map;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;

public class LotteryMachine {
    public static void main(String[] args) {
        LotteryPurchase lotteryPurchase = LotteryConverter.createLotteryPurchase();
        LotteryTickets lotteryTickets = LotteryConverter.createLotteryTickets(lotteryPurchase);
        OutputView.printLotteryTickets(lotteryPurchase, lotteryTickets);

        WinningLottery winningLottery =  LotteryConverter.createWinningLottery();
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(winningResult.getTotalProfitRate(lotteryPurchase));
    }
}
