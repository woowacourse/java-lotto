package lotterymachine;

import java.util.Collections;

import java.util.Map;
import lotterymachine.dto.ManualTicketDto;
import lotterymachine.model.LotteryResult;
import lotterymachine.model.TicketMachine;
import lotterymachine.model.WinningLottery;
import lotterymachine.model.WinningType;
import lotterymachine.vo.Count;
import lotterymachine.vo.LotteryMoney;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.model.LotteryTickets;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.List;

public class LotteryMachine {
    public static void main(String[] args) {
        LotteryMoney amount = getInputAmount();
        Count numberOfTickets = getNumberOfTickets(amount);
        Count numberOfManualTickets = getNumberOfManualTickets(numberOfTickets);
        LotteryTickets purchasedLotteryTickets = purchaseLotteryTickets(numberOfTickets, numberOfManualTickets);

        WinningLottery winningLottery = getWinningLottery();

        showResult(purchasedLotteryTickets, winningLottery);
    }

    private static LotteryMoney getInputAmount() {
        try {
            return LotteryMoney.fromInputAmount(InputView.getAmount());
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getInputAmount();
        }
    }

    private static Count getNumberOfTickets(LotteryMoney money) {
        return money.divideByLotteryPrice();
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
        LotteryResult lotteryResult = new LotteryResult(lotteryTickets, winningLottery);
        Map<WinningType, Count> ticketsResult = lotteryResult.compute();

        List<LotteryResultDto> lotteryResultDto = LotteryResultDto.createLotteryResults(ticketsResult);
        Collections.sort(lotteryResultDto);
        OutputView.printStatistics(lotteryResultDto);
        OutputView.printProfitRate(lotteryResult.getProfitRate(ticketsResult));
    }
}
