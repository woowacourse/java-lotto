package lotto.domain;

public class PurchaseAmount {

    public static final int LOTTO_PURCHASE_UNIT = 1000;

    public static boolean isNumber(int purchaseAmount) {
        return purchaseAmount / LOTTO_PURCHASE_UNIT == 0;
    }
}
