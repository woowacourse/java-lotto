package lotto.domain.purchaseamount;

import static lotto.util.regex.NumberRegex.isNaturalNumber;

public class TotalPurchaseAmount {
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 로또 가격의 양의 배수여야 합니다.";

    private final int amount;
    private final int lottoPrice;

    public TotalPurchaseAmount(final String amount, final int lottoPrice) {
        validateNaturalNumber(amount);
        int naturalNumberValue = Integer.parseInt(amount);
        validateMultipleOfPrice(naturalNumberValue, lottoPrice);
        this.amount = naturalNumberValue;
        this.lottoPrice = lottoPrice;
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

    public int getPurchaseCount() {
        return amount / lottoPrice;
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
