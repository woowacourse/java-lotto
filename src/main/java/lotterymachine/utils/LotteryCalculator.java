package lotterymachine.utils;

import static java.lang.Math.floor;

import java.util.List;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.model.Count;
import lotterymachine.model.Money;

public class LotteryCalculator {
    private static final int LOTTERY_PRICE = 1000;
    private static final double DECIMAL_PLACE_SAVER = 100.0;

    public static double calculateProfitRate(Money winningLotteryAmount, Money amount) {
        return floor(winningLotteryAmount.divide(amount) * DECIMAL_PLACE_SAVER)  / DECIMAL_PLACE_SAVER;
    }

    public static Money getTotalTicketAmount(Count numberOfTickets) {
        return Money.from(numberOfTickets.getNumber() * LOTTERY_PRICE);
    }

    public static Money getWinningAmount(List<LotteryResultDto> lotteryResult) {
        int sum = lotteryResult.stream()
                .map(LotteryResultDto::sumIncome)
                .map(Money::getAmount)
                .mapToInt(Integer::intValue)
                .sum();
        return Money.from(sum);
    }
}
