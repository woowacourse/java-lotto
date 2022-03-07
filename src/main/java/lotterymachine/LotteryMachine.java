package lotterymachine;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;

import java.util.Collections;

import java.util.Map;
import lotterymachine.dto.ManualTicketDto;
import lotterymachine.model.TicketMachine;
import lotterymachine.model.WinningLottery;
import lotterymachine.model.WinningType;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;
import lotterymachine.vo.Money;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.List;

public class LotteryMachine {
    public static void main(String[] args) {
        Money amount = getInputAmount();
        Count numberOfTickets = getNumberOfTickets(amount);
        Count numberOfManualTickets = getNumberOfManualTickets(numberOfTickets);
        LotteryTickets purchasedLotteryTickets = purchaseLotteryTickets(numberOfTickets, numberOfManualTickets);

        WinningLottery winningLottery = getWinningLottery();

        showResult(purchasedLotteryTickets, winningLottery);
    }

    private static Money getInputAmount() {
        try {
            return Money.fromInputAmount(InputView.getAmount());
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getInputAmount();
        }
    }

    private static Count getNumberOfTickets(Money money) {
        return LotteryCalculator.divideByLotteryPrice(money);
    }

    private static Count getNumberOfManualTickets(Count numberOfTickets) {
        try {
            int numberOfManualTickets = InputView.getNumberOfManualTickets();
            return Count.of(numberOfTickets, numberOfManualTickets);
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getNumberOfManualTickets(numberOfTickets);
        }
    }

    private static LotteryTickets purchaseLotteryTickets(Count numberOfTickets, Count numberOfManualTickets) {
        List<ManualTicketDto> manualTickets = InputView.getManualTickets(numberOfManualTickets);
        Count numberOfAutoTickets = numberOfTickets.subtract(numberOfManualTickets);

        TicketMachine ticketMachine = new TicketMachine();
        LotteryTickets manualLotteryTickets = ticketMachine.purchaseManualTickets(manualTickets);
        LotteryTickets autoLotteryTickets = ticketMachine.purchaseAutoTickets(numberOfAutoTickets);
        LotteryTickets lotteryTickets = LotteryTickets.merge(manualLotteryTickets, autoLotteryTickets);

        OutputView.printPurchaseDetails(numberOfManualTickets.getNumber(), numberOfAutoTickets.getNumber());
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());
        return lotteryTickets;
    }

    private static WinningLottery getWinningLottery() {
        try {
            return new WinningLottery(InputView.getWinningNumbers(), InputView.getBonusNumber());
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getWinningLottery();
        }
    }

    private static void showResult(LotteryTickets lotteryTickets, WinningLottery winningLottery) {
        LotteryTicket winningTicket = winningLottery.getWinningTicket();
        Ball bonusBall = winningLottery.getBonusBall();
        Map<WinningType, Count> ticketsResult = lotteryTickets.getLotteriesResult(winningTicket, bonusBall);
        List<LotteryResultDto> lotteryResult = LotteryResultDto.createLotteryResults(ticketsResult);
        Collections.sort(lotteryResult);
        OutputView.printStatistics(lotteryResult);

        Money totalTicketsAmount = LotteryCalculator.getTotalTicketAmount(lotteryTickets.getLotteryTickets().size());
        Money winningLotteryAmount = LotteryCalculator.getWinningAmount(lotteryResult);
        OutputView.printProfitRate(calculateProfitRate(winningLotteryAmount, totalTicketsAmount));
    }
}
