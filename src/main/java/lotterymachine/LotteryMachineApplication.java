package lotterymachine;

import lotterymachine.domain.*;
import lotterymachine.utils.LotteryNumbersGenerator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
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
        List<LotteryTicket> passivityTickets = LotteryTicket.from(InputView.getPassivityLotteryTicket(count.getAutoValue()));
        List<LotteryTicket> autoTickets = createAutoLotteryTickets(count.getAutoValue());
        return LotteryTickets.of(autoTickets, passivityTickets);
    }

    private static List<LotteryTicket> createAutoLotteryTickets(int count) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lotteryTickets.add(new LotteryTicket(LotteryNumber.getAutoValues()));
        }
        return lotteryTickets;
    }
}