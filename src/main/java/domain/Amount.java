package domain;

public class Amount {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Amount(int amount) {
        checkAmountPositive(amount);
        checkAmountDivisible(amount);
        this.amount = amount;
    }

    private void checkAmountPositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0원 이하는 입력할 수 없습니다.");
        }
    }

    private void checkAmountDivisible(int amount) {
        if (isDivisibleLottoPrice(amount)) {
            throw new IllegalArgumentException("1000원단위로 나누어 떨어지지 않습니다.");
        }
    }

    private boolean isDivisibleLottoPrice(int amount) {
        return amount % LOTTO_PRICE != 0;
    }

    public int getAmountDividedByLottoPrice() {
        return amount / LOTTO_PRICE;
    }

    public double getYield(long totalAmount) {
        return (double)totalAmount / amount;
    }
}
