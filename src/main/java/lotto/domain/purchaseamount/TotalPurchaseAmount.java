package lotto.domain.purchaseamount;

public class TotalPurchaseAmount {
    private static final String PURCHASE_AMOUNT_RANGE_EXCEPTION_MESSAGE = "로또 가격 보다 큰 값을 입력하셔야 합니다. 로또 가격은 ";
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 로또 가격의 양의 배수여야 합니다.";

    private final int amount;
    private final int lottoPrice;

    public TotalPurchaseAmount(final Builder builder) {
        this.lottoPrice = builder.lottoPrice;
        this.amount = builder.totalAmount;
    }

    public static class Builder {
        private int lottoPrice = 1000;
        private int totalAmount;

        public Builder setLottoPrice(final int lottoPrice) {
            this.lottoPrice = lottoPrice;
            return this;
        }

        public Builder setTotalAmount(final int totalAmount) {
            validateRange(totalAmount);
            validateMultipleOfPrice(totalAmount);
            this.totalAmount = totalAmount;
            return this;
        }

        private void validateRange(final int totalAmount) {
            if (totalAmount < lottoPrice) {
                throw new IllegalArgumentException(PURCHASE_AMOUNT_RANGE_EXCEPTION_MESSAGE + lottoPrice);
            }
        }

        private void validateMultipleOfPrice(final int purchaseAmount) {
            if (purchaseAmount % lottoPrice != 0) {
                throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
            }
        }

        public TotalPurchaseAmount build() {
            return new TotalPurchaseAmount(this);
        }
    }

    public int getTotalPurchaseCount() {
        return amount / lottoPrice;
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / amount;
    }
}
