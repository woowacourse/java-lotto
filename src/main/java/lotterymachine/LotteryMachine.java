package lotterymachine;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;
import static lotterymachine.utils.LotteryNumbersGenerator.generate;

import java.util.Collections;

import lotterymachine.model.Count;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.model.Money;
import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.utils.LotteryCalculator;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LotteryMachine {
    public static void main(String[] args) {
        Money amount = Money.from(InputView.getAmount());
        Count numberOfTickets = Count.calculateNumberOfTickets(amount.getAmount());
        OutputView.printNumberOfTicket(numberOfTickets.getNumber());

        LotteryTickets lotteryTickets = new LotteryTickets(createLotteryTickets(numberOfTickets));
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());

        List<LotteryResultDto> lotteryResult = getLotteryResult(lotteryTickets);
        Collections.sort(lotteryResult);
        printResult(numberOfTickets, lotteryResult);
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
        int bonusNumber = InputView.getBonusNumber(winningNumbers);
        return LotteryResultDto.createList(lotteryTickets.getLotteriesResult(winningNumbers, bonusNumber));
    }

    private static void printResult(Count numberOfTickets, List<LotteryResultDto> lotteryResult) {
        OutputView.printStatistics(lotteryResult);
        Money totalTicketsAmount = LotteryCalculator.getTotalTicketAmount(numberOfTickets);
        Money winningLotteryAmount = LotteryCalculator.getWinningAmount(lotteryResult);
        OutputView.printProfitRate(calculateProfitRate(winningLotteryAmount, totalTicketsAmount));
    }
}
