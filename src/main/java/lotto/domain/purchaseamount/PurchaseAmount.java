package lotto.domain.purchaseamount;

import static lotto.util.regex.NumberRegex.isNaturalNumber;

public class PurchaseAmount {
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 1000의 양의 배수여야 합니다.";
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    private PurchaseAmount(final int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount fromPurchaseAmountAndLottoPrice(final String amount) {
        validateNaturalNumber(amount);
        int naturalNumberValue = Integer.parseInt(amount);
        validateMultipleOfPrice(naturalNumberValue);
        return new PurchaseAmount(naturalNumberValue);
    }

    private static void validateNaturalNumber(final String value) {
        if (!isNaturalNumber(value)) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateMultipleOfPrice(final int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getPurchaseCount() {
        return amount / LOTTO_PRICE;
    }

    public int getCountOfLottoNumbers() {
        return amount / LOTTO_PRICE;
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / amount;
    }

    public boolean isLowerThan(final int numberOfManualLotto) {
        return amount < numberOfManualLotto * LOTTO_PRICE;
    }
}
