package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(int money) {
        validateNumber(money);
        this.money = money;
    }

    public int totalCountOfPurchaseLotto() {
        return money / LOTTO_PRICE;
    }

    public float calculateProfitRate(float profit) {
        return profit / money;
    }

    private void validateNumber(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("1000원 이상의 금액만 입력 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money1 = (Money) o;

        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return money;
    }
}
