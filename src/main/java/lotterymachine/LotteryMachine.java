package lotterymachine;

import static lotterymachine.utils.LotteryCalculator.calculateProfitRate;
import static lotterymachine.utils.LotteryGenerator.generate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        Count totalTickets = getNumberOfTickets(amount);
        Count manualTickets = getNumberOfManualTickets(totalTickets);
        LotteryTickets purchasedLotteryTickets = purchaseLotteryTickets(totalTickets, manualTickets);

        LotteryTicket winningTicket = getWinningTicket();
        int bonusNumber = InputView.getBonusNumber(winningTicket.getBalls());

        showResult(purchasedLotteryTickets, winningTicket, bonusNumber);
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
        return Count.from(money.divideByTicketPrice());
    }

    private static Count getNumberOfManualTickets(Count totalTickets) {
        try {
            int numberOfManualTickets = InputView.getNumberOfManualTickets();
            return Count.of(totalTickets, numberOfManualTickets);
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getNumberOfManualTickets(totalTickets);
        }
    }

    private static LotteryTickets purchaseLotteryTickets(Count numberOfTickets, Count manualTickets) {
        List<LotteryTicket> manualLotteryTickets = purchaseManualLotteryTickets(manualTickets);
        Count autoTickets = numberOfTickets.subtract(manualTickets);
        List<LotteryTicket> autoLotteryTickets = generate(autoTickets);
        LotteryTickets lotteryTickets = collectTickets(manualLotteryTickets, autoLotteryTickets);

        OutputView.printPurchaseDetails(manualTickets.getNumber(), autoTickets.getNumber());
        OutputView.printLotteryTickets(lotteryTickets.getLotteryTickets());
        return lotteryTickets;
    }

    private static LotteryTickets collectTickets(List<LotteryTicket> manualTickets, List<LotteryTicket> autoTickets) {
        return new LotteryTickets(Stream.of(manualTickets, autoTickets)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList()));
    }

    private static List<LotteryTicket> purchaseManualLotteryTickets(Count manualTickets) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        if (!manualTickets.isInteger()) {
            return lotteryTickets;
        }
        OutputView.printInputManualPurchase();
        for (int i = 0; i < manualTickets.getNumber(); i++) {
            lotteryTickets.add(getManualLotteryTicket());
        }
        return lotteryTickets;
    }

    private static LotteryTicket getManualLotteryTicket() {
        try {
            List<Ball> selectedBalls = Ball.createBalls(InputView.getManualTicket());
            return new LotteryTicket(selectedBalls);
        } catch (Exception exception) {
            OutputView.printException(exception.getMessage());
            return getManualLotteryTicket();
        }
    }

    private static LotteryTicket getWinningTicket() {
        try {
            List<Integer> winningNumbers = InputView.getWinningNumbers();
            return new LotteryTicket(Ball.createBalls(winningNumbers));
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getWinningTicket();
        }
    }

    private static void showResult(LotteryTickets lotteryTickets, LotteryTicket winningTicket, int bonusNumber) {
        Map<WinningLottery, Count> ticketsResult = lotteryTickets.getLotteriesResult(winningTicket,
                Ball.from(bonusNumber));
        List<LotteryResultDto> lotteryResult = LotteryResultDto.createLotteryResults(ticketsResult);
        Collections.sort(lotteryResult);
        OutputView.printStatistics(lotteryResult);

        Money totalTicketsAmount = LotteryCalculator.getTotalTicketAmount(lotteryTickets.getLotteryTickets().size());
        Money winningLotteryAmount = LotteryCalculator.getWinningAmount(lotteryResult);
        OutputView.printProfitRate(calculateProfitRate(winningLotteryAmount, totalTicketsAmount));
    }
}
