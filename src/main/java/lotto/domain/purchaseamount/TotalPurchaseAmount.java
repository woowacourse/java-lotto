package lotto.domain.purchaseamount;

public class TotalPurchaseAmount {
    private static final String PURCHASE_AMOUNT_RANGE_EXCEPTION_MESSAGE = "로또 가격 보다 큰 값을 입력하셔야 합니다. 로또 가격은 ";
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 로또 가격의 양의 배수여야 합니다.";
    private static final int LOTTO_NUMBER = 1000;

    private final int value;

    public TotalPurchaseAmount(final Builder builder) {
        this.value = builder.totalAmount;
    }

    public static class Builder {
        private int totalAmount;

        public Builder setTotalAmount(final int value) {
            validateRange(value);
            validateMultipleOfPrice(value);
            this.totalAmount = value;
            return this;
        }

        private void validateRange(final int value) {
            if (value < LOTTO_NUMBER) {
                throw new IllegalArgumentException(PURCHASE_AMOUNT_RANGE_EXCEPTION_MESSAGE + LOTTO_NUMBER);
            }
        }

        private void validateMultipleOfPrice(final int value) {
            if (value % LOTTO_NUMBER != 0) {
                throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
            }
        }

        public TotalPurchaseAmount build() {
            return new TotalPurchaseAmount(this);
        }
    }

    public int getTotalPurchaseCount() {
        return value / LOTTO_NUMBER;
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / value;
    }
}
