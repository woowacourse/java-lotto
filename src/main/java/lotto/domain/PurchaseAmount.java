package lotto.domain;

import java.util.regex.Pattern;

public class PurchaseAmount {
    private static final Pattern NATURAL_NUMBER_REGEX = Pattern.compile("^[1-9][0-9]*$");
    public static final String INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE = "구매 금액은 1000의 양의 배수여야 합니다.";

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
        if(purchaseAmount%lottoPrice!=0){
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_EXCEPTION_MESSAGE);
        }
    }
}
