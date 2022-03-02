package lotto.domain.purchaseamount;

import static lotto.util.regex.NumberRegex.isNaturalNumber;

public class TotalPurchaseAmount {
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 로또 가격의 양의 배수여야 합니다.";

    private final int amount;
    private final int lottoPrice;

    public TotalPurchaseAmount(final TotalPurchaseAmountBuilder totalPurchaseAmountBuilder) {
        validateNaturalNumber(totalPurchaseAmountBuilder.totalAmount);
        int naturalNumberValue = Integer.parseInt(totalPurchaseAmountBuilder.totalAmount);
        validateMultipleOfPrice(naturalNumberValue, totalPurchaseAmountBuilder.lottoPrice);
        this.amount = naturalNumberValue;
        this.lottoPrice = totalPurchaseAmountBuilder.lottoPrice;
    }

    public static class TotalPurchaseAmountBuilder {
        private String totalAmount = "5000";
        private int lottoPrice = 1000;

        public TotalPurchaseAmountBuilder setTotalAmount(final String totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public TotalPurchaseAmountBuilder setLottoPrice(final int lottoPrice) {
            this.lottoPrice = lottoPrice;
            return this;
        }

        public TotalPurchaseAmount build() {
            return new TotalPurchaseAmount(this);
        }
    }

    private void validateNaturalNumber(final String value) {
        if (!isNaturalNumber(value)) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    private void validateMultipleOfPrice(final int purchaseAmount, final int lottoPrice) {
        if (purchaseAmount % lottoPrice != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getCountOfLottoNumbers() {
        return amount / lottoPrice;
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / amount;
    }

    public boolean isLowerThan(final int numberOfManualLotto) {
        return amount < numberOfManualLotto * lottoPrice;
    }
}
