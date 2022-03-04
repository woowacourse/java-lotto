package lotterymachine.utils;

import static java.lang.Math.floor;

import java.util.List;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.vo.Money;

public class LotteryCalculator {
    private static final int LOTTERY_PRICE = 1000;
    private static final double DECIMAL_PLACE_SAVER = 100.0;

    public static double calculateProfitRate(Money winningLotteryAmount, Money amount) {
        return floor(winningLotteryAmount.divide(amount) * DECIMAL_PLACE_SAVER)  / DECIMAL_PLACE_SAVER;
    }

    public static Money getTotalTicketAmount(int numberOfTickets) {
        return Money.from(numberOfTickets * LOTTERY_PRICE);
    }

    public static Money getWinningAmount(List<LotteryResultDto> lotteryResults) {
        int sum = lotteryResults.stream()
                .map(LotteryCalculator::sumIncome)
                .mapToInt(Money::getAmount)
                .sum();
        return Money.from(sum);
    }

    private static Money sumIncome(LotteryResultDto lotteryResult) {
        return Money.from(lotteryResult.getWinningPrice() * lotteryResult.getNumberOfMatchingTicket());
    }
}
