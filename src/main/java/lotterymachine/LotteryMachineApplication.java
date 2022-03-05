package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.domain.vo.Count;
import lotterymachine.domain.vo.Money;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.List;


public class LotteryMachineApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.getAmount());
        Count count = new Count(InputView.getPassivityPurchaseCount(), money.getPurchasePossibleCount());
        LotteryTickets lotteryTickets = createLotteryTickets(count);
        OutputView.printLotteryPurchaseCount(count);
        OutputView.printLotteryTickets(lotteryTickets);

        LotteryTicket winningLotteryNumbers = LotteryTicket.from(InputView.getWinningLotteryNumbers());
        WinningLottery winningLottery = new WinningLottery(winningLotteryNumbers
                , LotteryNumber.from(InputView.getBonusNumber()));
        WinningResult winningResult = WinningResult.create(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(winningResult.getTotalProfitRate(money.getValue()));
    }

    private static LotteryTickets createLotteryTickets(Count count) {
        List<List<Integer>> passivityLotteryTicket = InputView.getPassivityLotteryTicket(count.getPassivityValue());
        List<LotteryTicket> passivityTickets = LotteryTicket.getLotteryTickets(passivityLotteryTicket);
        List<LotteryTicket> autoTickets = LotteryTicket.createAutoLotteryTickets(count.getAutoValue()
                , new RandomLotteryNumbersGenerator());
        return LotteryTickets.of(autoTickets, passivityTickets);
    }
}