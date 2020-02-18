package lotto.domain;

import lotto.view.OutputView;

public class PurchaseAmount {

    public static final int LOTTO_PURCHASE_UNIT = 1000;

    public static boolean isLottoPurchaseUnit(int purchaseAmount) {
        return purchaseAmount / LOTTO_PURCHASE_UNIT == 0;
    }

    public static boolean isNumber(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
