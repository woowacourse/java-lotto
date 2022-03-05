package lotterymachine;

import lotterymachine.domain.LotteryTicket;
import lotterymachine.domain.LotteryTickets;
import lotterymachine.domain.WinningLottery;
import lotterymachine.domain.WinningResult;
import lotterymachine.domain.vo.Count;
import lotterymachine.domain.vo.Money;
import lotterymachine.utils.RandomLotteryNumbersGenerator;
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

        WinningLottery winningLottery = new WinningLottery(InputView.getWinningLotteryNumbers(), InputView.getBonusNumber());
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        OutputView.printWinningLotteryResults(winningResult.getResult());
        OutputView.printProfitRate(winningResult.getTotalProfitRate(money));
    }

    private static LotteryTickets createLotteryTickets(Count count) {
        List<LotteryTicket> passivityTickets = LotteryTicket.from(InputView.getPassivityLotteryTicket(count.getPassivityValue()));
        List<LotteryTicket> autoTickets = LotteryTicket.createAutoLotteryTickets(count.getAutoValue()
                , new RandomLotteryNumbersGenerator());
        return LotteryTickets.of(autoTickets, passivityTickets);
    }
}