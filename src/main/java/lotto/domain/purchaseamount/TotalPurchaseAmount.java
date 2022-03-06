package lotto.domain.purchaseamount;

public class TotalPurchaseAmount {
    private static final String PURCHASE_AMOUNT_RANGE_EXCEPTION_MESSAGE = "로또 가격 보다 큰 값을 입력하셔야 합니다. 로또 가격은 ";
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 로또 가격의 양의 배수여야 합니다.";

    private final int value;

    public TotalPurchaseAmount(final int value, final int lottoPrice) {
        validateRange(value, lottoPrice);
        validateMultipleOfPrice(value, lottoPrice);
        this.value = value;
    }

    private void validateRange(final int value, final int lottoPrice) {
        if (value < lottoPrice) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_RANGE_EXCEPTION_MESSAGE + lottoPrice);
        }
    }

    private void validateMultipleOfPrice(final int value, final int lottoPrice) {
        if (value % lottoPrice != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getTotalPurchaseCount(final int lottoPrice) {
        return value / lottoPrice;
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / value;
    }
}
