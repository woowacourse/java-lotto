package lotterymachine.vo;

import java.util.Objects;

public class LotteryMoney {
    private static final int TICKET_PRICE = 1000;
    private static final String NOT_PURCHASABLE_EXCEPTION = "1000보다 큰 금액을 입력해야합니다.";

    private final int amount;

    private LotteryMoney(int amount) {
        this.amount = amount;
    }

    public static LotteryMoney from(int amount) {
        return new LotteryMoney(amount);
    }

    public static LotteryMoney fromInputAmount(int amount) {
        validate(amount);
        return new LotteryMoney(amount);
    }

    private static void validate(int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException(NOT_PURCHASABLE_EXCEPTION);
        }
    }

    public double divide(LotteryMoney money) {
        return (double) this.amount / money.amount;
    }

    public Count divideByLotteryPrice() {
        return Count.from(this.amount / TICKET_PRICE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LotteryMoney money = (LotteryMoney) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
