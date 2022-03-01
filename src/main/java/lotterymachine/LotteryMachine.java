package lotterymachine;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;
import static lotterymachine.utils.LotteryNumbersGenerator.generate;

import java.util.Collections;

import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;
import lotterymachine.vo.Money;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LotteryMachine {
    public static void main(String[] args) {
        Money amount = getInputAmount();
        Count numberOfTickets = Count.from(amount.divideByTicketPrice());
        OutputView.printNumberOfTicket(numberOfTickets.getNumber());

        LotteryTickets lotteryTickets = new LotteryTickets(createLotteryTickets(numberOfTickets));
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());

        List<LotteryResultDto> lotteryResult = getLotteryResult(lotteryTickets);
        Collections.sort(lotteryResult);
        printResult(numberOfTickets, lotteryResult);
    }

    private static Money getInputAmount() {
        try {
            return Money.fromInputAmount(InputView.getAmount());
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getInputAmount();
        }
    }

    private static List<LotteryTicket> createLotteryTickets(Count numberOfTickets) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets.getNumber(); i++) {
            lotteryTickets.add(new LotteryTicket(generate()));
        }
        return lotteryTickets;
    }

    private static List<LotteryResultDto> getLotteryResult(LotteryTickets lotteryTickets) {
        List<Integer> winningNumbers = InputView.getWinningLotteryNumbers();
        Ball bonus = Ball.from(InputView.getBonusNumber(winningNumbers));
        return LotteryResultDto.createLotteryResults(
                lotteryTickets.getLotteriesResult(Ball.createBalls(winningNumbers), bonus));
    }

    private static void printResult(Count numberOfTickets, List<LotteryResultDto> lotteryResult) {
        OutputView.printStatistics(lotteryResult);
        Money totalTicketsAmount = LotteryCalculator.getTotalTicketAmount(numberOfTickets);
        Money winningLotteryAmount = LotteryCalculator.getWinningAmount(lotteryResult);
        OutputView.printProfitRate(calculateProfitRate(winningLotteryAmount, totalTicketsAmount));
    }
}
