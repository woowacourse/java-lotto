package lotterymachine.utils;

import lotterymachine.vo.Count;
import lotterymachine.vo.Money;

public class LotteryCalculator {
    private static final int LOTTERY_PRICE = 1000;

    public static Money getTotalTicketAmount(int numberOfTickets) {
        return Money.from(numberOfTickets * LOTTERY_PRICE);
    }

    public static Count divideByLotteryPrice(Money money) {
         return Count.from(money.getAmount() / LOTTERY_PRICE);
    }
}
