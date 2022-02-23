package lotterymachine.utils;

public class LotteryCalculator {
    private static final int LOTTERY_PRICE = 1000;

    public static int divideByLotteryPrice(int amount) {
        return amount / LOTTERY_PRICE;
    }
}
