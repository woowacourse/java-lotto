package lotto.domain;

import static lotto.util.regex.NumberRegex.NATURAL_NUMBER_REGEX;

public class PurchaseAmount {
    private static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 1000의 양의 배수여야 합니다.";

    private final int amount;

    private PurchaseAmount(final int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount fromPurchaseAmountAndLottoPrice(final String amount, final int price) {
        validateNaturalNumber(amount);
        int naturalNumberValue = Integer.parseInt(amount);
        validateMultipleOfPrice(naturalNumberValue, price);
        return new PurchaseAmount(naturalNumberValue);
    }

    private static void validateNaturalNumber(final String value) {
        if (!NATURAL_NUMBER_REGEX.matcher(value).matches()) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateMultipleOfPrice(final int purchaseAmount, final int lottoPrice) {
        if (purchaseAmount % lottoPrice != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getPurchaseCount(final int lottoPrice) {
        return amount / lottoPrice;
    }

    public int getCountOfLottoNumbers(final int lottoPrice) {
        return amount / lottoPrice;
    }

    public double getProfitRate(final long totalProfit) {
        return (double) totalProfit / amount;
    }
}
