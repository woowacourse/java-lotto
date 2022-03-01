package lotterymachine;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;
import static lotterymachine.utils.LotteryGenerator.generate;

import java.util.Collections;

import java.util.Map;
import lotterymachine.model.WinningLottery;
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
        LotteryTickets purchasedLotteryTickets = purchaseLotteryTickets(numberOfTickets);

        List<Integer> winningNumbers = InputView.getWinningNumbers();
        int bonusNumber = InputView.getBonusNumber(winningNumbers);

        showResult(purchasedLotteryTickets, winningNumbers, bonusNumber);
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
        Count numberOfTickets = Count.from(money.divideByTicketPrice());
        OutputView.printNumberOfTicket(numberOfTickets.getNumber());
        return numberOfTickets;
    }

    private static LotteryTickets purchaseLotteryTickets(Count numberOfTickets) {
        List<LotteryTicket> lotteryTicketsList = generate(numberOfTickets);
        LotteryTickets lotteryTickets = new LotteryTickets(lotteryTicketsList);
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());
        return lotteryTickets;
    }

    private static void showResult(LotteryTickets lotteryTickets, List<Integer> winningNumbers, int bonusNumber) {
        Map<WinningLottery, Count> ticketsResult = lotteryTickets.getLotteriesResult(
                Ball.createBalls(winningNumbers), Ball.from(bonusNumber));
        List<LotteryResultDto> lotteryResult = LotteryResultDto.createLotteryResults(ticketsResult);
        Collections.sort(lotteryResult);
        OutputView.printStatistics(lotteryResult);

        Money totalTicketsAmount = LotteryCalculator.getTotalTicketAmount(lotteryTickets.getLotteryTickets().size());
        Money winningLotteryAmount = LotteryCalculator.getWinningAmount(lotteryResult);
        OutputView.printProfitRate(calculateProfitRate(winningLotteryAmount, totalTicketsAmount));
    }
}
