package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.domain.Count;
import lotterymachine.domain.Money;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.List;


public class LotteryMachineApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.getAmount());
        Count count = createCount(money);
        LotteryTickets lotteryTickets = createLotteryTickets(count);
        OutputView.printLotteryPurchaseCount(count);
        OutputView.printLotteryTickets(lotteryTickets);

        WinningLottery winningLottery = new WinningLottery(LotteryTicket.from(InputView.getWinningLotteryNumbers())
                , LotteryNumber.from(InputView.getBonusNumber()));
        WinningResult winningResult = WinningResult.create(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(Yield.of(winningResult.findTotalProfit(), money.getValue()).getProfitRate());
    }

    private static Count createCount(Money money) {
        int passivityPurchaseCount = InputView.getPassivityPurchaseCount();
        return new Count(passivityPurchaseCount, money.getPurchasePossibleCount() - passivityPurchaseCount, money.getPurchasePossibleCount());
    }

    private static LotteryTickets createLotteryTickets(Count count) {
        List<List<Integer>> passivityLotteryNumbers = InputView.getPassivityLotteryNumbers(count.getPassivityValue());
        List<LotteryTicket> passivityTickets = LotteryTicket.createLotteryTickets(passivityLotteryNumbers);
        List<LotteryTicket> autoTickets = LotteryTicket.createAutoLotteryTickets(count.getAutoValue()
                , new RandomLotteryNumbersGenerator());
        return LotteryTickets.of(autoTickets, passivityTickets);
    }
}