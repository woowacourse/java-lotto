package lotto.domain.purchaseamount;

import static lotto.util.regex.NumberRegex.isNaturalNumber;

public class TotalPurchaseAmount {
    private static final String PURCHASE_AMOUNT_NOT_DECIDED_EXCEPTION_MESSAGE =
            "구매 금액을 먼저 지정해야 수동 구매 로또 수를 입력할 수 있습니다.";
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 로또 가격의 양의 배수여야 합니다.";

    private final int amount;
    private final ManualPurchaseCount manualPurchaseCount;
    private final int lottoPrice;

    public TotalPurchaseAmount(final TotalPurchaseAmountBuilder totalPurchaseAmountBuilder) {
        this.lottoPrice = totalPurchaseAmountBuilder.lottoPrice;
        this.amount = totalPurchaseAmountBuilder.totalAmount;
        this.manualPurchaseCount = totalPurchaseAmountBuilder.manualPurchaseCount;
    }

    public static class TotalPurchaseAmountBuilder {
        private int totalAmount;
        private ManualPurchaseCount manualPurchaseCount;
        private int lottoPrice = 1000;

        public TotalPurchaseAmountBuilder setLottoPrice(final int lottoPrice) {
            this.lottoPrice = lottoPrice;
            return this;
        }

        public TotalPurchaseAmountBuilder setTotalAmount(final String totalAmount) {
            validateNaturalNumber(totalAmount);
            int parsedTotalAmount = Integer.parseInt(totalAmount);
            validateMultipleOfPrice(parsedTotalAmount);
            this.totalAmount = parsedTotalAmount;
            return this;
        }

        private void validateNaturalNumber(final String value) {
            if (!isNaturalNumber(value)) {
                throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
            }
        }

        private void validateMultipleOfPrice(final int purchaseAmount) {
            if (purchaseAmount % lottoPrice != 0) {
                throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
            }
        }

        public TotalPurchaseAmountBuilder setManualPurchaseAmount(final String manualPurchaseAmount) {
            validateNullTotalAmount();
            this.manualPurchaseCount = new ManualPurchaseCount(manualPurchaseAmount, totalAmount);
            return this;
        }

        private void validateNullTotalAmount() {
            if (totalAmount == 0) {
                throw new IllegalArgumentException(PURCHASE_AMOUNT_NOT_DECIDED_EXCEPTION_MESSAGE);
            }
        }

        public TotalPurchaseAmount build() {
            return new TotalPurchaseAmount(this);
        }
    }

    public int getCountOfLottoNumbers() {
        return amount / lottoPrice;
    }

    public int getCountOfManualLottoNumber() {
        return manualPurchaseCount.getValue();
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / amount;
    }
}
