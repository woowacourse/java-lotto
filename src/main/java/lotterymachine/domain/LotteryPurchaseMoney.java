package lotterymachine.domain;

import java.util.Objects;

public class LotteryPurchaseMoney {
    private static final String TERMS_OF_PURCHASE = "로또 구매는 기본 1000원 이상부터 할 수 있습니다.";

    private final int amount;

    public LotteryPurchaseMoney(int amount) {
        validateValue(amount);
        this.amount = amount;
    }

    private void validateValue(int value) {
        if (value < LotteryTicket.PER_PRICE) {
            throw new IllegalArgumentException(TERMS_OF_PURCHASE);
        }
    }

    public int getAmount() {
        return amount;
    }

    public int getPurchasePossibleCount() {
        return this.amount / LotteryTicket.PER_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryPurchaseMoney lotteryPurchaseMoney = (LotteryPurchaseMoney) o;
        return amount == lotteryPurchaseMoney.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
