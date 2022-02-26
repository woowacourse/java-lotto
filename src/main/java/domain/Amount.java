package domain;

public class Amount {
    private static final int LOTTO_PRICE = 1000;
    private static final String REQUEST_UNIT_OF_LOTTO_PRICE = String.format("%d원 단위로 입력해주세요.", LOTTO_PRICE);
    private final int amount;

    public Amount(int amount) {
        checkAmountPositive(amount);
        checkAmountLottoPriceUnit(amount);
        this.amount = amount;
    }

    private void checkAmountPositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("양수를 입력해주세요.");
        }
    }

    private void checkAmountLottoPriceUnit(int amount) {
        if (isDivisibleLottoPrice(amount)) {
            throw new IllegalArgumentException(REQUEST_UNIT_OF_LOTTO_PRICE);
        }
    }

    private boolean isDivisibleLottoPrice(int amount) {
        return amount % LOTTO_PRICE != 0;
    }

    public int getAmountDividedByLottoPrice() {
        return amount / LOTTO_PRICE;
    }

    public double getYield(long totalAmount) {
        return (double) totalAmount / amount;
    }
}
