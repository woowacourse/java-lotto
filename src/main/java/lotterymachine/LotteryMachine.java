package lotterymachine;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;
import static lotterymachine.utils.LotteryCalculator.divideByLotteryPrice;
import static lotterymachine.utils.LotteryNumbersGenerator.generate;
import static lotterymachine.utils.LotteryRule.TICKET_PRICE;

import java.util.Collections;

import lotterymachine.dto.Count;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.dto.Money;
import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.view.InputView;
import lotterymachine.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LotteryMachine {
    public static void main(String[] args) {
        Money amount = new Money(InputView.getAmount());
        Count numberOfTickets = new Count(divideByLotteryPrice(amount.getAmount()));
        OutputView.printNumberOfTicket(numberOfTickets);

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
        int ticketAmount = numberOfTickets.getNumber() * TICKET_PRICE.getNumber();
        int winningLotteryAmount = getWinningLotteryAmount(lotteryResult);
        OutputView.printProfitRate(calculateProfitRate(winningLotteryAmount, ticketAmount));
    }

    private static int getWinningLotteryAmount(List<LotteryResultDto> lotteryResult) {
        return lotteryResult.stream()
                .map(LotteryResultDto::sumIncome)
                .map(Money::getAmount)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
